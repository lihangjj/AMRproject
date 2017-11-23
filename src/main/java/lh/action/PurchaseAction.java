package lh.action;

import lh.service.IPurchaseService;
import lh.vo.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope(scopeName = "prototype")
@RequestMapping("/pages/purchase/*")
public class PurchaseAction extends AbstractActionAdapter {
    @Autowired
    private IPurchaseService purchaseService;

    @RequestMapping("add")
    public ModelAndView add(Purchase purchase, HttpServletRequest request) {
        purchase.setMeid((Integer) request.getSession().getAttribute("eid"));
        purchase.setEid((Integer) request.getSession().getAttribute("eid"));


        ModelAndView modelAndView = new ModelAndView("forward");
        try {
            if (purchaseService.insert(purchase)) {
                setMsgAndUrl("vo.add.success.msg", "purchase.list", modelAndView);
            } else {
                setMsgAndUrl("vo.add.failure.msg", "res.prebuy", modelAndView);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping("apply")
    ModelAndView listSplit(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/purchase/purchase_list");
        columnData = "申请标题:title|状态:status";
        handSplit(request);
        try {

            modelAndView.addAllObjects(purchaseService.listSplit(column, keyWord, currentPage, lineSize));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping("showDetails")
    ModelAndView showDetails(Purchase purchase) {
        ModelAndView modelAndView = new ModelAndView("/purchase/purchase_show");
        try {
            purchase = purchaseService.findById(purchase.getPid());
            modelAndView.addObject("purchase", purchase);
            System.out.println(purchase);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping("list")
    ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("/purchase/purchase_list");


        return modelAndView;
    }

    @Override
    String setUploadPath() {
        return null;
    }

    @Override
    String setUrl() {
        return "/pages/purchase/apply";
    }
}

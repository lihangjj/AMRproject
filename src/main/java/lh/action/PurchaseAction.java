package lh.action;

import lh.service.IPurchaseService;
import lh.vo.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping("audit")
    @ResponseBody
    boolean audit(Purchase purchase, HttpServletRequest request) {
        try {
            purchase.setMeid((Integer) request.getSession().getAttribute("eid"));
            return purchaseService.editStatus(purchase);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("showDetails")
    ModelAndView showDetails(Purchase purchase, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/purchase/purchase_show");
        try {
            purchase = purchaseService.findById(purchase.getPid());
            modelAndView.addObject("purchase", purchase);
            modelAndView.addObject("isFinance", purchaseService.isFinance((Integer) request.getSession().getAttribute("eid")));
            System.out.println(purchase);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping("list")
    ModelAndView list(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/purchase/purchase_list");

        handSplit(request);
        try {
            modelAndView.addAllObjects(purchaseService.simpleSplit(currentPage, lineSize));
        } catch (Exception e) {
            e.printStackTrace();
        }

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

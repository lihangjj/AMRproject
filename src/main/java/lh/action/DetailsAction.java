package lh.action;

import lh.service.IDetailsService;
import lh.service.ITypeService;
import lh.vo.Details;
import lh.vo.Emp;
import lh.vo.Subtype;
import lh.vo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Scope(scopeName = "prototype")
@RequestMapping("/pages/res/*")
public class DetailsAction extends AbstractActionAdapter {

    @Autowired
    private ITypeService typeService;
    @Autowired
    private IDetailsService detailsService;


    @RequestMapping("addPre")
    ModelAndView addPre() {
        ModelAndView modelAndView = new ModelAndView("/res/res_add");
        try {
            modelAndView.addObject("allType", typeService.list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping("getSubtype")
    @ResponseBody
    List<Subtype> getSubtype(Type type) {
        try {
            return typeService.findSubTypeByTid(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("add")
    ModelAndView add(Details details, MultipartFile pic, HttpServletRequest request) {
        details.setEid((Integer) request.getSession().getAttribute("eid"));
        String photoName = createFileName(pic);
        details.setPhoto(photoName);
        System.out.println(details);

        ModelAndView modelAndView = new ModelAndView("forward");
        title = "商品清单";
        try {
            if (detailsService.add(details)) {
                saveFile(photoName, pic, request);
                setMsgAndUrl("vo.add.success.msg", "res.addPre", modelAndView);
            } else {
                setMsgAndUrl("vo.add.failure.msg", "res.addPre", modelAndView);
            }
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping("prebuy")
    ModelAndView prebuy(Emp emp, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/res/res_prebuy");
        try {
            emp.setEid((Integer) request.getSession().getAttribute("eid"));
            modelAndView.addObject("allDetails", detailsService.listPrebuy(emp));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping("editPre")
    ModelAndView editPre(Details details) {

        ModelAndView modelAndView = new ModelAndView("/res/res_edit");
        try {
            Details details1 = detailsService.findById(details);


            System.out.println(details1);
            modelAndView.addObject("details", details1);
            modelAndView.addObject("allType", typeService.list());
            modelAndView.addObject("allSubtype", typeService.findSubTypeByTid(details1.getType()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping("checkTitle")
    @ResponseBody
    boolean checkTitle(Details details, HttpServletRequest request) {
        if (details.getTitle().equals(request.getParameter("oldTitle"))) {
            return true;
        }
        try {
            return !detailsService.checkTitle(details);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("edit")
    ModelAndView edit(Details details, MultipartFile pic, HttpServletRequest request) {
        String fileName = null;

        if (!pic.isEmpty()) {
            fileName = createFileName(pic);
            details.setPhoto(fileName);
        }
        System.out.println(details);

        ModelAndView modelAndView = new ModelAndView("forward");
        try {
            title = "待购商品";
            if (detailsService.edit(details)) {
                if (!pic.isEmpty()) {
                    saveFile(fileName, pic, request);
                }
                setMsgAndUrl("vo.edit.success.msg", "res.prebuy", modelAndView);

            } else {
                setMsgAndUrl("vo.edit.failure.msg", "res.prebuy", modelAndView);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping("editAmount")
    @ResponseBody
    boolean editAmount(HttpServletRequest request) {
        String updateStr = request.getParameter("updatestr");
        String deleteStr = request.getParameter("deletestr");
        String[] deleteDid = deleteStr.split("\\|");
        boolean f = false;
        if ("".equals(deleteStr)) {
            f = true;
        } else {
            try {
                f = detailsService.deleteDetails(deleteDid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String[] didAndAmount = updateStr.split("\\|");

        int y = 0;
        for (String x : didAndAmount) {
            Details details = new Details();
            details.setDid(Integer.valueOf(x.split(":")[0]));
            details.setAmount(Integer.valueOf(x.split(":")[1]));
            try {
                if (detailsService.updateAmount(details)) {
                    y++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return y == didAndAmount.length && f;


    }

    @RequestMapping("rm")
    @ResponseBody
    boolean rm(HttpServletRequest request) {
        String deleteStr = request.getParameter("deletestr");
        String[] deleteDid = deleteStr.split("\\|");
        if ("".equals(deleteStr)) {
            return true;
        } else {
            try {
                return detailsService.deleteDetails(deleteDid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    String setUploadPath() {
        return "/upload/res/";
    }

    @Override
    String setUrl() {
        return null;
    }
}

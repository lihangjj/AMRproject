package lh.action;

import lh.service.IEmpService;
import lh.util.password.EncryptUtil;
import lh.vo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope(scopeName = "prototype")
@RequestMapping("/pages/admin/")
public class AdminAction extends AbstractAction {
    @Autowired
    private IEmpService empService;

    @RequestMapping("addPre")
    public ModelAndView addPre() {
        ModelAndView modelAndView = new ModelAndView("/admin/admin_add");

        try {
            modelAndView.addObject("allLevels", empService.findAllLevel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping("checkEid")
    @ResponseBody
    public boolean checkEid(HttpServletRequest request) {
        String eid = request.getParameter("eid");

        try {
            return empService.findById(Integer.valueOf(eid));
        } catch (Exception e) {
            return false;
        }

    }

    @RequestMapping("checkSalary")
    @ResponseBody
    public boolean checkSalary(Emp emp) {
        try {
            return empService.checkSal(emp.getLid(), emp.getSalary());
        } catch (Exception e) {
            return false;
        }

    }

    @RequestMapping("add")
    public ModelAndView add(Emp emp, MultipartFile pic, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("forward");
        emp.setPassword(EncryptUtil.getPwd(emp.getPassword()));
        String fileName = createFileName(pic);
        emp.setPhoto(fileName);
        int heid = (int) request.getSession().getAttribute("eid");
        emp.setHeid(heid);
        title = "员工";
        try {
            if (empService.insert(emp)) {
                setMsgAndUrl("vo.add.success.msg", "emp.addPre", modelAndView);
                saveFile(fileName, pic, request);
            } else {
                setMsgAndUrl("vo.add.failure.msg", "emp.addPre", modelAndView);
            }
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @Override
    String setUploadPath() {
        return "/upload/emp/";
    }
}

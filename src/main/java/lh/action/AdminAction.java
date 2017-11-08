package lh.action;

import lh.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope(scopeName = "prototype")
@RequestMapping("/pages/admin/")
public class AdminAction {
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
}

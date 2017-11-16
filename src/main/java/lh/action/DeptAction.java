package lh.action;

import lh.service.IDeptService;
import lh.vo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope(scopeName = "prototype")
@RequestMapping("/pages/")
public class DeptAction extends AbstractAction {
    @Autowired
    private IDeptService deptService;

    @RequestMapping("dept/list")
    public ModelAndView list(HttpServletRequest request) {
        if (verifyPermission(4, request)) {
            ModelAndView modelAndView = new ModelAndView("dept/dept_list");
            try {
                modelAndView.addObject("allDept", deptService.list());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("forward");
            setMsgAndUrl("noPermission.msg", "login.success.page", modelAndView);
            return modelAndView;
        }

    }

    @RequestMapping("dept/edit")
    @ResponseBody
    public boolean edit(Dept dept, HttpServletRequest request) {
        if (verifyPermission(7, request)) {
            try {
                return deptService.update(dept);
            } catch (Exception e) {
                return false;
            }

        }
        return false;
    }

    @RequestMapping("groups/list")
    public ModelAndView actionList(HttpServletRequest request, Dept dept) {
        if (verifyPermission(6, request)) {
            ModelAndView modelAndView = new ModelAndView("action/action_list");
            try {
                modelAndView.addObject("allAction", deptService.findActionByDid(dept.getDid()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("forward");
            setMsgAndUrl("noPermission.msg", "login.success.page", modelAndView);
            return modelAndView;
        }

    }

}

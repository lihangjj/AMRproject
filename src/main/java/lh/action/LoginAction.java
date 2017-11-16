package lh.action;

import lh.service.IEmpService;
import lh.util.MD5Code;
import lh.vo.Action;
import lh.vo.Emp;
import lh.vo.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@Scope(scopeName = "prototype")//取消单例设计模式
public class LoginAction extends AbstractAction {
    @Autowired
    private IEmpService empService;

    @RequestMapping("/login")
    public ModelAndView login(Emp emp, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("forward");
        emp.setPassword(new MD5Code().getMD5ofStr(emp.getPassword()));
        title = "员工";
        try {
            if (empService.findLogin(emp)) {
                HttpSession session = request.getSession();
                session.setAttribute("eid", emp.getEid());
                session.setAttribute("name", emp.getName());
                session.setAttribute("photo", emp.getPhoto());
                session.setAttribute("aflag", emp.getAflag());
                List<Groups> groups = empService.findGroupsByDid(emp.getDid());
                Set<Integer> actids = new HashSet<>();
                for (Groups x : groups) {
                    List<Action> actions = x.getActions();
                    for (Action y : actions) {
                        actids.add(y.getActid());
                    }
                }
                session.setAttribute("actids", actids);
                session.setAttribute("groups", groups);
                setMsgAndUrl("login.success", "login.success.page", modelAndView);
            } else {
                setMsgAndUrl("login.failure", "login.page", modelAndView);
            }
        } catch (Exception e) {
            setMsgAndUrl("login.failure.error", "login.page", modelAndView);
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping("/pages/invalidate")
    public ModelAndView invalidate(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("forward");
        setMsgAndUrl("invalidate.success", "login.page", modelAndView);
        request.getSession().invalidate();
        return modelAndView;
    }


}

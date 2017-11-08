package lh.action;

import lh.service.IEmpService;
import lh.util.MD5Code;
import lh.vo.Emp;
import lh.vo.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    @Override
    String setUploadPath() {
        return null;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//这里一般都只穿日期就够了
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
    }

}

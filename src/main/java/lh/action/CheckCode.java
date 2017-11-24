package lh.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CheckCode {
    @RequestMapping("CheckCode")
    @ResponseBody
    private boolean checkCode(HttpServletRequest request) {
        String code = request.getParameter("code");
        if (code == null || "".equals(code)) {
            return false;
        } else {
            String rand= (String) request.getSession().getAttribute("rand");
            if (code.equalsIgnoreCase(rand)) {
                return true;
            }
        }
        return false;
    }
}

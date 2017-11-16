package lh.util;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ValidationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println("验证之前拦截");
        //验证，并得到验证错误结果，如果map长度大于0，说明有错，就拦截，长度等于0，就放行
        HandlerMethod handlerMethod = null;
        try {
            handlerMethod = (HandlerMethod) o;
        } catch (Exception e) {
            return true;//如果不能强制换成HandlerMethod,说明不需要拦截，直接放行；
        }
        Map<String, String> errorsMap = validateUtil.getErrorsMap(request,  handlerMethod);
        if (errorsMap.size() == 0) {
            return true;//放行
        }
        String path = "/pages/errors.jsp";
        if (errorsMap.get("errorsPage") != null) {//看是否设置了指定的错误页路径
            path = errorsMap.get("errorsPage");
            errorsMap.remove("errorsPage");//移除最后添加的那条页面路径
        }
        request.setAttribute("errors", errorsMap);
        request.getRequestDispatcher(path).forward(request, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

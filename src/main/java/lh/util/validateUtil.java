package lh.util;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class validateUtil {
    public static Map<String, String> getErrorsMap(HttpServletRequest request, HandlerMethod o) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        HandlerMethod handlerMethod = o;
        Method getResourceValue = handlerMethod.getBeanType().getMethod("getResourceValue", String.class, Object[].class);
        Object object = handlerMethod.getBean();
        String className = handlerMethod.getBeanType().getSimpleName();
        String methodName = handlerMethod.getMethod().getName();
        String key = className + "." + methodName + ".rules";//规则的key
        String errorsPageKey = className + "." + methodName + ".errors.page";//错误页的key

        String errorsPage = (String) getResourceValue.invoke(object, errorsPageKey, null);//错误页路径

        Map<String, String> errorsMap = new HashMap<>();//用来保存错误的map

        //取得与当前方法需要的验证规则
        String validateRules = (String) getResourceValue.invoke(object, key, null);
        if (validateRules != null) {
            String nameValue[] = validateRules.split("\\|");
            for (String x : nameValue) {
                String name = x.split(":")[0];
                String rule = x.split(":")[1];
                String value = request.getParameter(name);
                if (isEmpty(value)) {
                    errorsMap.put(name, (String) getResourceValue.invoke(object, "validate.string.msg", null));
                } else {
                    String msg = (String) getResourceValue.invoke(object, "validate." + rule + ".msg", null);
                    switch (rule) {
                        case "int":
                            if (!value.matches("\\d+")) {
                                errorsMap.put(name, msg);
                            }
                            break;
                        case "double":
                            if (!value.matches("\\d+(\\.\\d{1,2})?")) {
                                errorsMap.put(name, msg);
                            }
                            break;
                        case "date":
                            if (!value.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                errorsMap.put(name, msg);
                            }
                            break;
                        case "datetime":
                            if (!value.matches("\\d{4}-\\d{2}-\\d{2} [0-1][0-9]|[2][0-3]:[0-5][0-9]:[0-5][0-9]")) {//时间正则
                                errorsMap.put(name, msg);
                            }
                            break;
                        case "rand":
                            if (!value.equals(request.getSession().getAttribute("rand"))) {//验证码验证
                                errorsMap.put(name, msg);
                            }
                            break;
                    }
                }


            }
            if (errorsPage != null && errorsMap.size() > 0) {//已经出现错误才设置错误页，否则不要设置错误页，因为后面的判断是根据
                // map的长度来判断是不是有错
                errorsMap.put("errorsPage", errorsPage);
            }
        }

        if (errorsMap.size() == 0) {//如果前面的信息都没有错，那就开始验证上传文件
            boolean flag = validateFileMime(request, getResourceValue, object, className, methodName);
            if (!flag) {
                String mimeKey = className + "." + methodName + ".mime.rules";
                String mimeRules = (String) getResourceValue.invoke(object, mimeKey, null);
                if (mimeRules == null) {//表示没有设置单独的验证规则，就用公共的验证规则
                    mimeRules = (String) getResourceValue.invoke(object, "mime.rules", null);
                }
                errorsMap.put("文件类型不支持，请确定文件类型为以下类型：", mimeRules);
            }

        }
        return errorsMap;
    }

    private static boolean validateFileMime(HttpServletRequest request, Method getResourceValue, Object object, String className, String methodName) throws IllegalAccessException, InvocationTargetException {
        MultipartResolver multipartResolver = new CommonsMultipartResolver();
        if (multipartResolver.isMultipart(request)) {//表示如果有上传文件
            String mimeKey = className + "." + methodName + ".mime.rules";
            String mimeRules = (String) getResourceValue.invoke(object, mimeKey, null);
            if (mimeRules == null) {//表示没有设置单独的验证规则，就用公共的验证规则
                mimeRules = (String) getResourceValue.invoke(object, "mime.rules", null);
            }
            MultipartRequest mreq = (MultipartRequest) request;
            Map<String, MultipartFile> fileMap = mreq.getFileMap();
            if (fileMap.size() > 0) {
                Collection<MultipartFile> collection = fileMap.values();
                Iterator<MultipartFile> iterator = collection.iterator();
                String[] mimeType = mimeRules.split("\\|");
                int count = 0;
                while (iterator.hasNext()) {
                    MultipartFile file = iterator.next();
                    if (file.getSize() > 0) {//表示此时有上传文件
                        for (String x : mimeType) {
                            if (x.equals(file.getContentType())) {//此时文件符合mime类型
                                count++;
                            }

                        }
                    } else {//此时没有文件上传，默认为合格文件
                        count++;
                    }
                }
                return count == collection.size();
            }
        }
        return true;
    }

    public static boolean isEmpty(String value) {
        if (value == null || "".equals(value)) {
            return true;
        }
        return false;
    }
}

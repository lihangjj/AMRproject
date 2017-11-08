package lh.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public abstract class AbstractAction {
    protected String title ="";
    @Autowired
    private ResourceBundleMessageSource resourceBundleMessageSource;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    public void setMsgAndUrl(String msg, String url, ModelAndView modelAndView) {
        modelAndView.addObject("msg", getResourceValue(msg, title));
        modelAndView.addObject("url", getResourceValue(url, title));
    }

    public String getResourceValue(String key, Object... agr) {
        try {
            return resourceBundleMessageSource.getMessage(key, agr, Locale.getDefault());
        } catch (Exception e) {
            return null;//如果处异常，说明资源没有这key,直接返回null
        }
    }

    public String createFileName(MultipartFile photo) {
        if (photo.isEmpty()) {
            return "nophoto.png";
        }
        return UUID.randomUUID() + "." + photo.getContentType().split("/")[1];
    }

    public boolean deleteFile(String fileName, HttpServletRequest request) {
        String filePath = request.getSession().getServletContext().getRealPath("/") + setUploadPath() + fileName;
        File file = new File(filePath);
        return file.delete();
    }

    public boolean saveFile(String fileName, MultipartFile photo, HttpServletRequest request) {
        String filePath = request.getSession().getServletContext().getRealPath("/") + setUploadPath() + fileName;
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            InputStream inputStream = photo.getInputStream();
            int temp;
            byte[] bytes = new byte[2048];
            while ((temp = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, temp);
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    abstract String setUploadPath();

}

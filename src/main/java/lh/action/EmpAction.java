package lh.action;

import lh.service.IEmpService;
import lh.util.password.EncryptUtil;
import lh.vo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope(scopeName = "prototype")
@RequestMapping("/pages/emp/")
public class EmpAction extends AbstractActionAdapter {
    @Autowired
    private IEmpService empService;

    @RequestMapping("addPre")
    public ModelAndView addPre(HttpServletRequest request) {
        if (verifyPermission(11, request)) {
            ModelAndView modelAndView = new ModelAndView("emp/emp_add");
            try {
                modelAndView.addAllObjects(empService.addPre());
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

    @RequestMapping("add")
    public ModelAndView add(HttpServletRequest request, Emp emp, MultipartFile pic) {
        ModelAndView modelAndView = new ModelAndView("forward");
        if (verifyPermission(12, request)) {
//            String fileName = createFileName(pic);
//            emp.setPhoto(fileName);//设置照片名字
            emp.setPassword(EncryptUtil.getPwd(emp.getPassword()));//密码加密
            emp.setHeid((Integer) request.getSession().getAttribute("eid"));
            try {
                title = "雇员";
                if (empService.insertEmp(emp)) {
//                    saveFile(fileName, pic, request);
                    setMsgAndUrl("vo.add.success.msg", "emp.addPre", modelAndView);
                } else {
                    setMsgAndUrl("vo.add.failure.msg", "emp.addPre", modelAndView);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return modelAndView;
        } else {
            setMsgAndUrl("noPermission.msg", "login.success.page", modelAndView);
            return modelAndView;
        }
    }

    @RequestMapping("list")
    public ModelAndView list(HttpServletRequest request) {
        if (verifyPermission(13, request)) {
            ModelAndView modelAndView = new ModelAndView("emp/emp_list");
            try {
                columnData = "管理员编号:eid|管理员名字:name|管理员电话:phone|管理员工资:salary";
                url = "/pages/emp/list";
                handSplit(request);
                modelAndView.addAllObjects(empService.listSplitEmp(column, keyWord, currentPage, lineSize));
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

    @RequestMapping("editPre")
    public ModelAndView editPre(HttpServletRequest request, Emp emp) {

        if (verifyPermission(14, request)) {
            ModelAndView modelAndView = new ModelAndView("/emp/emp_edit");

            try {
                modelAndView.addObject("emp", empService.findEmpById(emp.getEid()));
                modelAndView.addAllObjects(empService.addPre());
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

    @RequestMapping("edit")
    public ModelAndView edit(HttpServletRequest request, Emp emp, MultipartFile pic) {
        if (verifyPermission(15, request)) {
            String fileName = createFileName(pic);
            emp.setPhoto(fileName);
            emp.setHeid((Integer) request.getSession().getAttribute("eid"));
            ModelAndView modelAndView = new ModelAndView("forward");
            try {
                title="雇员";
                if (empService.update(emp)) {

                    setMsgAndUrl("vo.edit.success.msg", "emp.list", modelAndView);

                } else {
                    setMsgAndUrl("vo.edit.failure.msg", "emp.list", modelAndView);
                }
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

    @Override
    String setUploadPath() {
        return "/upload/emp/";
    }
}

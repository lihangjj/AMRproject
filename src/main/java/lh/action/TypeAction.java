package lh.action;

import lh.service.ITypeService;
import lh.vo.Subtype;
import lh.vo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope(scopeName = "prototype")
@RequestMapping("/pages/type/*")
public class TypeAction extends AbstractAction {
    @Autowired
    private ITypeService typeService;

    @RequestMapping("list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("/type/type_list");
        try {
            modelAndView.addObject("allType", typeService.list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping("edit")
    @ResponseBody
    public boolean edit(Type type) {
        System.out.println(type);
        try {
            return typeService.edit(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("listSubtype")
    private ModelAndView listSubtype(Type type) {
        ModelAndView modelAndView = new ModelAndView("/type/subtype_list");
        try {
            modelAndView.addObject("allSubtype", typeService.findSubTypeByTid(type));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @RequestMapping("editSubtype")
    @ResponseBody
    boolean editSubtype(Subtype subtype) {

        try {
            return typeService.editSubtype(subtype);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

package lh.action;

import lh.service.IMemberService;
import lh.vo.Member;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Scope(scopeName = "prototype")//取消单例设计模式
@RequestMapping("/pages/member/*")
public class MemberAction extends AbstractAction {
    @Autowired
    private IMemberService memberService;
    private Logger log = Logger.getLogger(MemberAction.class);

    @RequestMapping("add")
    public ModelAndView add(Member member) {
        log.info(member);
        try {
            log.info(memberService.add(member));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

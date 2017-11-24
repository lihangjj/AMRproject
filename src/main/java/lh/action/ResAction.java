package lh.action;

import lh.service.IResService;
import lh.service.ITakeService;
import lh.vo.Details;
import lh.vo.Res;
import lh.vo.Take;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope(scopeName = "prototype")
@RequestMapping("/pages/res/*")
public class ResAction extends AbstractActionAdapter {
    @Autowired
    private IResService resService;
    @Autowired
    private ITakeService takeService;
    private String url;

    @RequestMapping("list")
    public ModelAndView list(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/res/res_list");
        try {
            columnData = "名称:title|状态:status";
            url = "/pages/res/list";
            handSplit(request);
            modelAndView.addAllObjects(resService.listSplit(currentPage, lineSize, column, keyWord));
            System.out.println(resService.isXingzheng((Integer) request.getSession().getAttribute("eid")) + "是否行政");
            modelAndView.addObject("isXingzheng", resService.isXingzheng((Integer) request.getSession().getAttribute("eid")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping("append")
    @ResponseBody
    boolean append(Res res, HttpServletRequest request) {
        Details details = new Details();
        details.setRid(res.getRid());
        details.setEid((Integer) request.getSession().getAttribute("eid"));
        try {
            return resService.updateAppendAmount(details);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @ResponseBody
    @RequestMapping("addTake")
    boolean addTake(HttpServletRequest request, Take take) {
        take.setGeid((Integer) request.getSession().getAttribute("eid"));
        System.out.println(take);
        try {
            return takeService.add(take);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }

    @RequestMapping("preget")
    ModelAndView preget(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/res/res_preget");
        try {
            modelAndView.addAllObjects(takeService.listUget((Integer) request.getSession().getAttribute("eid")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("editTake")
    boolean editTake(HttpServletRequest request) {
        String s = request.getParameter("data");
        String[] tkidAndAmount = s.split("\\|");
        int count = 0;
        for (String x : tkidAndAmount) {
            Take take = new Take();
            take.setGeid((Integer) request.getSession().getAttribute("eid"));
            take.setAmount(Integer.valueOf(x.split(":")[1]));
            take.setTkid(Integer.valueOf(x.split(":")[0]));
            if (take.getAmount() != 0) {
                try {
                    if (takeService.editTake(take)) {
                        count++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    if (takeService.remove(take.getTkid())) {
                        count++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return count == tkidAndAmount.length;
    }

    @ResponseBody
    @RequestMapping("rmTake")
    boolean rmTake(HttpServletRequest request) {
        String s = request.getParameter("data");
        String[] tkidAndAmount = s.split("\\|");
        int count = 0;
        for (String x : tkidAndAmount) {
            try {
                if (takeService.remove(Integer.valueOf(x))) {
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count == tkidAndAmount.length;
    }

    @RequestMapping("get")
    @ResponseBody
    boolean get(HttpServletRequest request) {
        try {
            return takeService.updateStatus((Integer) request.getSession().getAttribute("eid"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("emp_list")
    ModelAndView emp_list(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/res/res_emp_list");
        try {
            url = "/pages/res/emp_list";
            handSplit(request);
            modelAndView.addAllObjects(takeService.listSplitByStatus((Integer) request.getSession().getAttribute("eid"), currentPage, lineSize));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping("audit")
    ModelAndView audit(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/res/res_audit");
        try {
            url = "/pages/res/audit";
            columnData = "领取人:geid|状态:status";

            handSplit(request);
            modelAndView.addAllObjects(takeService.listSplit((Integer) request.getSession().getAttribute("eid"), column, keyWord, currentPage, lineSize));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @RequestMapping("editAudit")
    @ResponseBody
    boolean editAudit(Take take) {
        try {
            return takeService.editStatus(take);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @RequestMapping("editRflag")
    @ResponseBody
    boolean editRflag(Take take) {
        try {
            return takeService.editRflag(take);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("editRdate")
    @ResponseBody
    boolean editRdate(Take take) {
        try {
            return takeService.editRdate(take);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override

    String setUploadPath() {
        return null;
    }

    @Override
    String setUrl() {
        return this.url;
    }
}

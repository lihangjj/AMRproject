package lh.service.impl;

import lh.dao.IDetailsDAO;
import lh.dao.IEmpDAO;
import lh.dao.IResDAO;
import lh.service.IResService;
import lh.vo.Details;
import lh.vo.Emp;
import lh.vo.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResServiceImpl implements IResService {
    @Autowired
    private IResDAO resDAO;
    @Autowired
    private IEmpDAO empDAO;
    @Autowired
    private IDetailsDAO detailsDAO;

    @Override
    public boolean insert(Res res) throws Exception {
        return resDAO.doCreate(res);
    }

    @Override
    public Map<String, Object> listSplit(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> resMap = new HashMap<>();
        map.put("keyWord", "%" + keyWord + "%");
        if ("".equals(keyWord) || keyWord == null) {
            map.put("keyWord", null);
        }
        map.put("column", column);
        map.put("lineSize", lineSize);
        map.put("start", (currentPage - 1) * lineSize);
        resMap.put("allRes", resDAO.listSplit(map));
        resMap.put("allRecorders", resDAO.listSplitCount(map));
        return resMap;
    }

    @Override
    public boolean isXingzheng(int eid) throws Exception {
        Emp emp = empDAO.findById(eid);
        return emp.getDid() == 3;
    }

    @Override
    public boolean updateAppendAmount(Details details) throws Exception {
        Details d = resDAO.findDetailsByRid(details);
        if (d == null) {
            Res res = resDAO.findById(details.getRid());
            details.setAmount(1);
            details.setRes(res);
            details.setPhoto(res.getPhoto());
            details.setTid(res.getTid());
            details.setStid(res.getStid());
            details.setRflag(res.getRflag());
            details.setPrice(res.getPrice());
            details.setTitle(res.getTitle());
            return detailsDAO.doCreate(details);
        } else {
            return resDAO.updateAppendAmount(d.getDid());
        }

    }
}

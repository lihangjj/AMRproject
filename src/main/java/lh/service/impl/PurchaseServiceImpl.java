package lh.service.impl;

import lh.dao.IDetailsDAO;
import lh.dao.IEmpDAO;
import lh.dao.IPurchaseDAO;
import lh.service.IPurchaseService;
import lh.util.MathUtil;
import lh.vo.Details;
import lh.vo.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseServiceImpl implements IPurchaseService {
    @Autowired
    private IPurchaseDAO purchaseDAO;
    @Autowired
    private IDetailsDAO detailsDAO;
    @Autowired
    private IEmpDAO empDAO;

    @Override
    public boolean insert(Purchase purchase) throws Exception {
        purchase.setPubdate(new Date());
        purchase.setStatus(0);//设置待审核状态
        double total = 0;
        List<Details> allDetails = detailsDAO.findAllPrebuy(purchase.getEid());
        for (Details x : allDetails) {
            total += x.getPrice() * x.getAmount();
            purchase.setEid(x.getEid());

        }
        purchase.setTotal(MathUtil.round(total, 2));//保留两位小数
        if (purchaseDAO.doCreate(purchase)) {
            Details details = new Details();
            details.setPid(purchase.getPid());
            details.setEid(purchase.getEid());
            return detailsDAO.updatePid(details);
        }
        return false;
    }

    @Override
    public Map<String, Object> listSplit(String column, String keyWord, int currentPage, int lineSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("keyWord", "%" + keyWord + "%");
        if ("".equals(keyWord) || keyWord == null) {
            map.put("keyWord", null);
        }
        map.put("lineSize", lineSize);
        map.put("column", column);
        map.put("start", (currentPage - 1) * lineSize);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("allRecorders", purchaseDAO.listSplitCount(map));
        resMap.put("allPurchases", purchaseDAO.listSplit(map));
        return resMap;
    }

    @Override
    public Purchase findById(int pid) throws Exception {
        Purchase purchase=purchaseDAO.findById(pid);
        purchase.setEmp(empDAO.findById(purchase.getEid()));
        purchase.setAllDetails(detailsDAO.findByPid(pid));
        return purchaseDAO.findById(pid);
    }


}

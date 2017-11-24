package lh.service.impl;

import lh.dao.IResDAO;
import lh.dao.ITakeDAO;
import lh.service.ITakeService;
import lh.vo.Res;
import lh.vo.Take;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TakeServiceImpl implements ITakeService {
    @Autowired
    private ITakeDAO takeDAO;
    @Autowired
    private IResDAO resDAO;

    @Override
    public boolean add(Take take) throws Exception {
        Take t = takeDAO.findByEidAndRid(take);
        if (t == null) {
            take.setAmount(1);
            return takeDAO.doCreate(take);
        } else {
            return takeDAO.doUpdateAmount(t);
        }
    }

    @Override
    public boolean editTake(Take take) throws Exception {
        return takeDAO.editTake(take);
    }

    @Override
    public Map<String, Object> listUget(int geid) throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<Take> allTakes = takeDAO.findByGeid(geid);
        List<Res> allRes = new ArrayList<>();
        Map<Integer, Take> allTakess = new HashMap<>();
        for (Take x : allTakes) {
            Res res = resDAO.findById(x.getRid());
            allRes.add(res);
            allTakess.put(res.getRid(), x);
        }
        map.put("allRes", allRes);
        map.put("allTakes", allTakess);
        return map;
    }

    @Override
    public boolean remove(int tkid) throws Exception {
        return takeDAO.delete(tkid);
    }

    @Override
    public boolean updateStatus(int eid) throws Exception {
        List<Take> allTakes = takeDAO.findByGeid(eid);
        int count = 0;
        for (Take x : allTakes) {
            x.setStatus(0);
            if (takeDAO.updateStatus(x)) {
                count++;
            }
        }
        return count == allTakes.size();
    }

    @Override
    public boolean editStatus(Take take) throws Exception {
        take.setGdate(new Date());
        Take take1 = takeDAO.findById(take.getTkid());
        Res res = resDAO.findById(take1.getRid());
        if (take.getStatus() == 1) {
            if (res.getAmount() < take1.getAmount()) {
                return false;
            }
            if (takeDAO.doUpdateStatus(take)) {
                res.setAmount(res.getAmount() - take1.getAmount());
                return resDAO.updateResAmount(res);
            }
        } else {
            return takeDAO.doUpdateStatus(take);
        }


        return false;
    }

    @Override
    public Map<String, Object> listSplitByStatus(int geid, int currentPage, int lineSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("start", (currentPage - 1) * lineSize);
        map.put("lineSize", lineSize);
        map.put("geid", geid);
        Map<String, Object> resMap = new HashMap<>();


        List<Take> allTake = takeDAO.findByGeidAndStatus(map);
        Map<Integer, Res> resMap1 = new HashMap<>();
        for (Take x : allTake) {
            resMap1.put(x.getTkid(), resDAO.findById(x.getRid()));
        }
        resMap.put("allRecorders", takeDAO.findByGeidAndStatusCount(map));
        resMap.put("allTake", allTake);
        resMap.put("resMap", resMap1);
        return resMap;
    }

    @Override
    public Map<String, Object> listSplit(int geid, String column, String keyWord, int currentPage, int lineSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("start", (currentPage - 1) * lineSize);
        map.put("lineSize", lineSize);
        map.put("keyWord", "%" + keyWord + "%");
        if ("".equals(keyWord) || keyWord == null) {
            map.put("keyWord", null);
        }
        map.put("column", column);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("allRecorders", takeDAO.listSplitCount(map));
        List<Take> allTake = takeDAO.listSplit(map);
        Map<Integer, Res> resMap1 = new HashMap<>();
        for (Take x : allTake) {
            resMap1.put(x.getTkid(), resDAO.findById(x.getRid()));
        }
        resMap.put("allTake", allTake);
        resMap.put("resMap", resMap1);
        return resMap;
    }

    @Override
    public boolean editRflag(Take take) throws Exception {
        Take take1 = takeDAO.findById(take.getTkid());
        take1.setStatus(3);//归还申请
        return takeDAO.editRflag(take1);
    }

    @Override
    public boolean editRdate(Take take) throws Exception {
        Take take1 = takeDAO.findById(take.getTkid());
        Res res = resDAO.findById(take1.getRid());
        take1.setStatus(4);//已经归还
        take1.setRdate(new Date());
        if (takeDAO.updateStatus(take1)) {
            res.setAmount(res.getAmount() + take1.getAmount());
            return resDAO.updateResAmount(res);
        }
        return false;
    }
}

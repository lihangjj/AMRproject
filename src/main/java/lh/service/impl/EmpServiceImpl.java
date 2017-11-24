package lh.service.impl;

import lh.dao.IEmpDAO;
import lh.dao.ILevelDAO;
import lh.service.IEmpService;
import lh.util.password.EncryptUtil;
import lh.vo.Action;
import lh.vo.Emp;
import lh.vo.Groups;
import lh.vo.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements IEmpService {
    @Autowired
    private IEmpDAO empDAO;
    @Autowired
    private ILevelDAO levelDAO;

    @Override
    public Map<String, Object> addPre() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("allDept", empDAO.findDeptBySflag());
        map.put("allLevel", empDAO.findAllLevel());
        return map;
    }

    @Override
    public Map<String, Object> listSplit(String column, String keyWord, int currentPage, int lineSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> mapParameter = new HashMap<>();
        mapParameter.put("keyWord", "%" + keyWord + "%");
        if (keyWord == null || "".equals(keyWord)) {//如果keyWord为空，手工put null
            mapParameter.put("keyWord", null);
        }
        mapParameter.put("column", column);
        mapParameter.put("start", (currentPage - 1) * lineSize);
        mapParameter.put("lineSize", lineSize);

        map.put("allAdmin", empDAO.findAllAdminSplit(mapParameter));
        map.put("allRecorders", empDAO.getAllAdminSplitCount(mapParameter));
        return map;
    }

    @Override
    public boolean findLogin(Emp emp) throws Exception {
        Emp emp1 = empDAO.findLogin(emp);
        if (emp1 != null) {
            emp.setAflag(emp1.getAflag());
            emp.setName(emp1.getName());
            emp.setPhoto(emp1.getPhoto());
            emp.setDid(emp1.getDid());
            return true;
        }
        return false;
    }

    @Override
    public List<Groups> findGroupsByDid(int did) throws Exception {
        List<Groups> groups = empDAO.findGroupsByEid(did);
        for (Groups x : groups) {
            List<Action> actions = empDAO.findActionsByGid(x.getGid());
            x.setActions(actions);
        }
        return groups;
    }

    @Override
    public List<Level> findAllLevel() throws Exception {
        return empDAO.findAllLevel();
    }

    @Override
    public boolean insert(Emp emp) throws Exception {
        if (checkSal(emp.getLid(), emp.getSalary())) {//业务层先验证工资
            emp.setAflag(2);//普通管理员
            return empDAO.doCreate(emp);
        }
        return false;

    }

    @Override
    public Map<String, Object> listSplitEmp(String column, String keyWord, int currentPage, int lineSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> mapParameter = new HashMap<>();
        mapParameter.put("keyWord", "%" + keyWord + "%");
        if (keyWord == null || "".equals(keyWord)) {
            mapParameter.put("keyWord", null);
        }
        mapParameter.put("lineSize", lineSize);
        mapParameter.put("start", (currentPage - 1) * lineSize);
        mapParameter.put("column", column);
        map.put("allEmp", empDAO.findAllEmpSplit(mapParameter));
        map.put("allRecorders", empDAO.getAllEmpSplitCount(mapParameter));
        return map;
    }

    @Override
    public boolean insertEmp(Emp emp) throws Exception {
        if (checkSal(emp.getLid(), emp.getSalary())) {
            emp.setAflag(0);
            return empDAO.doCreate(emp);
        }
        return false;

    }

    @Override
    public boolean findByName(Emp emp) throws Exception {

        if (empDAO.findByName(emp) > 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Emp emp) throws Exception {
        emp.setAflag(0);
        if ("".equals(emp.getPassword()) || emp.getPassword() == null) {
            emp.setPassword(null);
        } else {
            emp.setPassword(EncryptUtil.getPwd(emp.getPassword()));
        }
        return empDAO.doUpdate(emp);
    }

    @Override
    public boolean findById(int eid) throws Exception {
        if (empDAO.findById(eid) != null) {
            return false;
        }
        return true;
    }

    @Override
    public Emp findEmpById(int eid) throws Exception {
        return empDAO.findById(eid);
    }

    @Override
    public boolean checkSal(int lid, double sal) throws Exception {
        Level level = levelDAO.findById(lid);
        if (level.getLosal() <= sal && sal <= level.getHisal()) {
            return true;
        }
        return false;
    }

}

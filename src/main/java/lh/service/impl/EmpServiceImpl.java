package lh.service.impl;

import lh.dao.IEmpDAO;
import lh.dao.ILevelDAO;
import lh.service.IEmpService;
import lh.vo.Action;
import lh.vo.Emp;
import lh.vo.Groups;
import lh.vo.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements IEmpService {
    @Autowired
    private IEmpDAO empDAO;
    @Autowired
    private ILevelDAO levelDAO;

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
        emp.setAflag(2);//普通管理员
        return empDAO.doCreate(emp);
    }

    @Override
    public boolean findById(int eid) throws Exception {
        if (empDAO.findById(eid) != null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkSal(int lid, double sal) throws Exception {
        Level level=levelDAO.findById(lid);
        if (level.getLosal()<=sal&&sal<=level.getHisal()){
            return true;
        }
        return false;
    }

}

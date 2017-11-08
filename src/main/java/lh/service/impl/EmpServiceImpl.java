package lh.service.impl;

import lh.dao.IEmpDAO;
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
}

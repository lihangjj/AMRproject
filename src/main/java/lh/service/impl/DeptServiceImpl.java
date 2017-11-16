package lh.service.impl;

import lh.dao.IDeptDAO;
import lh.service.IDeptService;
import lh.vo.Action;
import lh.vo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements IDeptService {
    @Autowired
    private IDeptDAO deptDAO;

    @Override
    public List<Dept> list() throws Exception {
        return deptDAO.findAll();
    }

    @Override
    public boolean update(Dept dept) throws Exception {
        return deptDAO.doUpdate(dept);
    }

    @Override
    public List<Action> findActionByDid(int did) throws Exception {
        return deptDAO.findActionByDid(did);
    }
}

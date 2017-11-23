package lh.dao;

import lh.vo.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IEmpDAO extends IDAO<Integer, Emp> {
    Emp findLogin(Emp emp);

    List<Groups> findGroupsByEid(Integer did);

    List<Action> findActionsByGid(int gid);

    List<Level> findAllLevel();

    List<Dept> findDeptBySflag();

    boolean doCreate(Emp emp);

    List<Emp> findAllEmpSplit(Map<String, Object> map);

    List<Emp> findAllAdminSplit(Map<String, Object> map);

    Integer getAllAdminSplitCount(Map<String, Object> map);

    Integer getAllEmpSplitCount(Map<String, Object> map);

    int findByName(Emp emp);

}

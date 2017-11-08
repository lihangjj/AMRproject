package lh.dao;

import lh.vo.Action;
import lh.vo.Emp;
import lh.vo.Groups;
import lh.vo.Level;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmpDAO extends IDAO<Integer, Emp> {
    Emp findLogin(Emp emp);
    List<Groups> findGroupsByEid(Integer did);
    List<Action> findActionsByGid(int gid) throws Exception;
    List<Level>  findAllLevel()throws Exception;
}

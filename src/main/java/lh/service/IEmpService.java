package lh.service;

import lh.vo.Action;
import lh.vo.Emp;
import lh.vo.Groups;
import lh.vo.Level;

import java.util.List;


public interface IEmpService {
    boolean findLogin(Emp emp) throws Exception;

    List<Groups> findGroupsByDid(int did) throws Exception;

    List<Level> findAllLevel() throws Exception;


}

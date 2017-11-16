package lh.service;

import lh.vo.Emp;
import lh.vo.Groups;
import lh.vo.Level;

import java.util.List;
import java.util.Map;


public interface IEmpService {
    boolean findLogin(Emp emp) throws Exception;

    List<Groups> findGroupsByDid(int did) throws Exception;

    List<Level> findAllLevel() throws Exception;

    boolean insert(Emp emp) throws Exception;

    boolean insertEmp(Emp emp) throws Exception;
    boolean update(Emp emp) throws Exception;

    boolean findById(int eid) throws Exception;

    Emp findEmpById(int eid) throws Exception;

    boolean checkSal(int lid, double sal) throws Exception;

    Map<String, Object> addPre() throws Exception;

    Map<String, Object> listSplit(String column, String keyWord, int currentPage, int lineSize) throws Exception;

    Map<String, Object> listSplitEmp(String column, String keyWord, int currentPage, int lineSize) throws Exception;
}

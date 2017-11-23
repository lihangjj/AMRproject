package lh.service;

import lh.vo.Action;
import lh.vo.Dept;

import java.util.List;

public interface IDeptService {
    List<Dept> list() throws Exception;
    boolean update(Dept dept)throws Exception;
    List<Action> findActionByDid(int did)throws Exception;

}

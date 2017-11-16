package lh.dao;

import lh.vo.Action;
import lh.vo.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDeptDAO extends IDAO<Integer,Dept> {
    List<Action> findActionByDid(Integer did);

}

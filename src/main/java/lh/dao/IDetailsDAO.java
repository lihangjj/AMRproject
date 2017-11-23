package lh.dao;

import lh.vo.Details;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDetailsDAO extends IDAO<Integer, Details> {
    List<Details> findAllPrebuy(Integer emp) throws Exception;

    Integer findByTitle(Details details) throws Exception;

    boolean updateAmount(Details details) throws Exception;

    boolean deleteDetails(String[] dids) throws Exception;

    boolean updatePid(Details details) throws Exception;

    List<Details> findByPid(Integer pid) throws Exception;
}

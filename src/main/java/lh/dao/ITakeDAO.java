package lh.dao;

import lh.vo.Take;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ITakeDAO extends IDAO<Integer, Take> {
    Take findByEidAndRid(Take take);

    boolean doUpdateAmount(Take t);

    List<Take> findByGeid(Integer geid);

    boolean delete(Integer tkid);

    boolean editTake(Take take);

    boolean updateStatus(Take take);

    List<Take> findByGeidAndStatus(Map<String, Object> map);

    Integer findByGeidAndStatusCount(Map<String, Object> map);

    List<Take> listSplit(Map<String, Object> map);

    Integer listSplitCount(Map<String, Object> map);
    boolean doUpdateStatus(Take take);
    boolean editRflag(Take take);
}

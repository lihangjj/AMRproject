package lh.dao;

import lh.vo.Details;
import lh.vo.Res;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IResDAO extends IDAO<Integer, Res> {
    List<Res> listSplit(Map<String, Object> map);

    int listSplitCount(Map<String, Object> map);

    boolean updateAppendAmount(Integer did);

    boolean updateResAmount(Res res);

    Details findDetailsByRid(Details details);
}

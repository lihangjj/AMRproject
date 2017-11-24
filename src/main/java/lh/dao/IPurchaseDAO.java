package lh.dao;

import lh.vo.Purchase;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IPurchaseDAO extends IDAO<Integer, Purchase> {
    List<Purchase> listSplit(Map<String, Object> map);

    Integer listSplitCount(Map<String, Object> map);

    List<Purchase> simpleSplit(Map<String, Object> map);

    Integer simpleSplitCount();

    boolean editStatus(Purchase purchase);
}

package lh.service;

import lh.vo.Details;
import lh.vo.Res;

import java.util.Map;

public interface IResService {
    boolean insert(Res res) throws Exception;

    Map<String, Object> listSplit(int currentPage, int lineSize, String column, String keyWord) throws Exception;

    boolean isXingzheng(int eid)throws Exception;
    boolean updateAppendAmount(Details did)throws Exception;
}

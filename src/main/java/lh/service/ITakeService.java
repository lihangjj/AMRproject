package lh.service;

import lh.vo.Take;

import java.util.Map;

public interface ITakeService {
    boolean add(Take take) throws Exception;

    boolean editTake(Take take) throws Exception;

    Map<String, Object> listUget(int geid) throws Exception;

    boolean remove(int tkid) throws Exception;

    boolean updateStatus(int eid) throws Exception;

    boolean editStatus(Take take) throws Exception;

    Map<String, Object> listSplitByStatus(int geid, int currentPage, int lineSize) throws Exception;

    Map<String, Object> listSplit(int geid, String column, String keyWord, int currentPage, int lineSize) throws Exception;

    boolean editRflag(Take take)throws Exception;

    boolean editRdate(Take take)throws Exception;
}

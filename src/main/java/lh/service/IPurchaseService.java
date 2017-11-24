package lh.service;

import lh.vo.Purchase;

import java.util.Map;

public interface IPurchaseService {
    boolean insert(Purchase purchase) throws Exception;

    Map<String, Object> listSplit(String column, String keyWord, int currentPage, int lineSize) throws Exception;

    Purchase findById(int pid) throws Exception;

    Map<String,Object> simpleSplit(int currentPage,int lineSize)throws Exception;
    boolean isFinance(int eid)throws Exception;

    boolean editStatus(Purchase purchase)throws Exception;

}

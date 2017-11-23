package lh.service;

import lh.vo.Details;
import lh.vo.Emp;

import java.util.List;

public interface IDetailsService {
    boolean add(Details details) throws Exception;

    List<Details> listPrebuy(Emp emp) throws Exception;

    Details findById(Details details) throws Exception;

    boolean edit(Details details) throws Exception;
    boolean checkTitle(Details details)throws Exception;
    boolean updateAmount(Details details) throws Exception;
    boolean deleteDetails(String[] dids)throws Exception;
}

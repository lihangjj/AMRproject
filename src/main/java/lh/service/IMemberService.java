package lh.service;

import lh.vo.Member;

import java.util.List;

public interface IMemberService {
    boolean add(Member vo) throws Exception;
    List<Member> split(String column, String keyWord, int currentPage, int lineSize);

}

package lh.service.impl;

import lh.dao.IMemberDAO;
import lh.service.IMemberService;
import lh.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements IMemberService {
    @Autowired
    private IMemberDAO iMemberDAO;

    @Override
    public boolean add(Member vo) throws Exception {
        return iMemberDAO.doCreate(vo);
    }
    @Override
    public List<Member> split(String column, String keyWord, int currentPage, int lineSize) {
        Map<String, Object> map=new HashMap<>();
        map.put("column",column);
        map.put("keyWord",keyWord);
        map.put("start",(currentPage-1)*lineSize);
        map.put("lineSize",lineSize);
        return iMemberDAO.findAllSplit(map);
    }
}

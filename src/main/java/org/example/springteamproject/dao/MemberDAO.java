package org.example.springteamproject.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.springteamproject.mapper.MemberMapper;
import org.example.springteamproject.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
//    @Autowired
//    private SqlSession sqlSession;
//
//    public MemberVO getMemberByName(String username) {
//        return sqlSession.selectOne("member.getMemberByName", username);
//    }
//
//    public boolean checkUserPassword(MemberVO user) {
//        return sqlSession.selectOne("member.checkUserPassword", user);
//    }

    @Autowired
    private MemberMapper memberMapper;

    public MemberVO getMemberByName(String username) {
        return memberMapper.getMemberByName(username);
    }

    public boolean checkUserPassword(MemberVO user) {
//        return memberMapper.checkUserPassword(user);
        if (memberMapper.checkUserPassword(user)==1)
            return true;
        return false;
    }
}

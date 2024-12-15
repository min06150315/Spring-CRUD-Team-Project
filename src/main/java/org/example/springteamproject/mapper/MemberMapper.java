package org.example.springteamproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.example.springteamproject.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface MemberMapper {

    public MemberVO getMemberByName(String username);

    public int checkUserPassword(MemberVO user);
}

package org.example.springteamproject.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.springteamproject.vo.ProblemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProblemDAO {

    @Autowired
    SqlSession sqlSession;

    public int insertProblem(ProblemVO vo) {
        int result = sqlSession.insert("Problem.insertProblem", vo);
        return result;
    }

    public int deleteProblem(int id) {
        int result = sqlSession.delete("Problem.deleteProblem", id);
        return result;
    }

    public int updateProblem(ProblemVO vo) {
        int result = sqlSession.update("Problem.updateProblem", vo);
        return result;
    }

    public ProblemVO getProblem(int id) {
        ProblemVO one = sqlSession.selectOne("Problem.getProblem", id);
        return one;
    }

    public List<ProblemVO> getProblemList() {
        List<ProblemVO> list = sqlSession.selectList("Problem.getProblemList");
        return list;
    }

    public int getTotalCnt() {
        int totalCount = sqlSession.selectOne("Problem.getTotalCnt");  // selectOne 쿼리 실행
        return totalCount;
    }
}

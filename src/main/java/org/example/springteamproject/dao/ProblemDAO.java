package org.example.springteamproject.dao;

import org.example.springteamproject.mapper.ProblemMapper;
import org.example.springteamproject.vo.ProblemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProblemDAO {

    @Autowired
    private ProblemMapper problemMapper;

    public int insertProblem(ProblemVO vo) {
        return problemMapper.insertProblem(vo);
    }

    public int deleteProblem(int id) {
        return problemMapper.deleteProblem(id);
    }

    public int updateProblem(ProblemVO vo) {
        return problemMapper.updateProblem(vo);
    }

    public ProblemVO getProblem(int id) {
        return problemMapper.getProblem(id);
    }

    public int getTotalCnt() {
        return problemMapper.getTotalCnt();
    }

    public List<ProblemVO> getProblemList() {
        return problemMapper.getProblemList();
    }
}

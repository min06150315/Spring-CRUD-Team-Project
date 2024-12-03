package org.example.springteamproject.service;

import org.example.springteamproject.dao.ProblemDAO;
import org.example.springteamproject.vo.BoardVO;
import org.example.springteamproject.vo.ProblemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService {

    @Autowired
    ProblemDAO problemDAO;

    public int insertProblem(ProblemVO vo) {
        return problemDAO.insertProblem(vo);
    }

    public int deleteProblem(int id) {
        return problemDAO.deleteProblem(id);
    }

    public int updateProblem(ProblemVO vo) {
        return problemDAO.updateProblem(vo);
    }

    public ProblemVO getProblem(int seq) {
        return problemDAO.getProblem(seq);
    }

    public List<ProblemVO> getProblemList() {
        return problemDAO.getProblemList();
    }

    public int getTotalcnt() {
        return problemDAO.getTotalcnt();
    }
}
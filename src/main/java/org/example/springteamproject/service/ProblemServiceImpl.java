package org.example.springteamproject.service;

import org.example.springteamproject.dao.ProblemDAO;
import org.example.springteamproject.vo.ProblemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    ProblemDAO problemDAO;

    @Override
    public int insertProblem(ProblemVO vo) {
        System.out.println("insertProblem()");
        return problemDAO.insertProblem(vo);
    }

    @Override
    public int deleteProblem(int id) {
        System.out.println("deleteProblem()");
        return problemDAO.deleteProblem(id);
    }

    @Override
    public int updateProblem(ProblemVO vo) {
        System.out.println("updateProblem()");
        return problemDAO.updateProblem(vo);
    }

    @Override
    public ProblemVO getProblem(int id) {
        return problemDAO.getProblem(id);
    }

    @Override
    public List<ProblemVO> getProblemList() {
        return problemDAO.getProblemList();
    }

    @Override
    public int getTotalCnt() {
        return problemDAO.getTotalCnt();
    }
}
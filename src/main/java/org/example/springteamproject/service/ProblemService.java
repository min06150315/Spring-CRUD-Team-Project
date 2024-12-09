package org.example.springteamproject.service;

import org.example.springteamproject.vo.ProblemVO;

import java.util.List;

public interface ProblemService {
    int insertProblem(ProblemVO vo);
    int deleteProblem(int id);
    int updateProblem(ProblemVO vo);
    ProblemVO getProblem(int id);
    List<ProblemVO> getProblemList();
    int getTotalCnt();
}

package org.example.springteamproject.service;

import org.example.springteamproject.dao.ProblemDAO;
import org.example.springteamproject.vo.ProblemVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProblemService {
    public int insertProblem(ProblemVO vo);
    public int deleteProblem(int id);
    public int updateProblem(ProblemVO vo);
    public ProblemVO getProblem(int id);
    public List<ProblemVO> getProblemList();
    public int getTotalCnt();
}

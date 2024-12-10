package org.example.springteamproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.springteamproject.vo.ProblemVO;

import java.util.List;

@Mapper
public interface ProblemMapper {
    int insertProblem(ProblemVO vo);
    int deleteProblem(int id);
    int updateProblem(ProblemVO vo);
    ProblemVO getProblem(int id);
    int getTotalCnt();
    List<ProblemVO> getProblemList();
    List<ProblemVO> searchProblems(String keyword);
}

package org.example.springteamproject.controller;

import org.example.springteamproject.service.ProblemServiceImpl;
import org.example.springteamproject.vo.ProblemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProblemController {

    @Autowired
    ProblemServiceImpl problemService;

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/problem/list", method = RequestMethod.GET)
    public String problemList(Model model){
        model.addAttribute("totalcnt", problemService.getTotalCnt());
        model.addAttribute("list", problemService.getProblemList());
        return "posts";
    }

    @RequestMapping("/problem/view/{id}")
    public String problemView(@PathVariable("id") Integer id, Model model) {
        ProblemVO problemVO = problemService.getProblem(id);
        model.addAttribute("problemVO", problemVO);
        return "view";
    }

    @RequestMapping(value = "/problem/add", method = RequestMethod.GET)
    public String problemAdd() {
        return "addpostform";
    }

    @RequestMapping(value = "problem/addok", method = RequestMethod.POST)
    public String problemAddOK(ProblemVO vo) {
        int i = problemService.insertProblem(vo);
        if (i == 0)
            System.out.println("데이터 추가 실패!");
        else
            System.out.println("데이터 추가 성공!");
        return "redirect:list";
    }

    @RequestMapping(value = "/problem/edit/{id}", method = RequestMethod.GET)
    public String problemEdit(@PathVariable("id") Integer id, Model model) {
        ProblemVO problemVO = problemService.getProblem(id);
        model.addAttribute("problemVO", problemVO);
        return "editform";
    }

    @RequestMapping(value = "/problem/editok", method = RequestMethod.POST)
    public String problemEditOK(@ModelAttribute ProblemVO problemVO) {
        int i = problemService.updateProblem(problemVO);
        if (i == 0)
            System.out.println("데이터 수정 실패!");
        else {
            System.out.println("데이터 수정 성공!");
        }
        return "redirect:list";
    }

    @RequestMapping(value = "/problem/delete/{id}", method = RequestMethod.GET)
    public String problemDelete(@PathVariable("id") Integer id) {
        int i = problemService.deleteProblem(id);

        return "redirect:../list";
    }








}



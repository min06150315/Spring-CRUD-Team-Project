package org.example.springteamproject.controller;

import org.example.springteamproject.service.ProblemService;
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
    ProblemService problemService;

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/problem/list", method = RequestMethod.GET)
    public String problemList(Model model){
        model.addAttribute("totalcnt", problemService.getTotalCnt());
        model.addAttribute("list", problemService.getProblemList());
        return "list";
    }

    @RequestMapping("/problem/view/{id}")
    public String problemView(@PathVariable("id") Integer id, Model model) {
        ProblemVO problemVO = problemService.getProblem(id);
        model.addAttribute("problemVO", problemVO);
        return "view";
    }

    @RequestMapping(value = "/problem/add", method = RequestMethod.GET)
    public String problemAdd() {
        return "add";
    }

    @RequestMapping(value = "/problem/addok", method = RequestMethod.POST)
    public String problemAddOK(ProblemVO vo) {
        System.out.println("Received VO: " + vo); // 디버그용 로그

        if (vo.getTitle() == null || vo.getTitle().isEmpty()) {src/main/java/org/example/springteamproject/controller/ProblemController.java

            System.out.println("Title is null or empty!");
            return "redirect:add?error=title_missing";
        }

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
        return "edit";
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



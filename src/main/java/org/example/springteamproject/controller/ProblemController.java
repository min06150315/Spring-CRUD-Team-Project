package org.example.springteamproject.controller;

import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.example.springteamproject.service.ProblemService;
import org.example.springteamproject.vo.ProblemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class ProblemController {

    private final String UPLOAD_DIR = new File("").getAbsolutePath() + "/upload";

    @Autowired
    ProblemService problemService;

    @RequestMapping("/")
    public String home() {
        return "home/index";
    }

    @RequestMapping(value = "/problem/list", method = RequestMethod.GET)
    public String problemList(Model model) {
        model.addAttribute("totalcnt", problemService.getTotalCnt());
        model.addAttribute("list", problemService.getProblemList());
        return "problem/list";
    }

    @RequestMapping("/problem/view/{id}")
    public String problemView(@PathVariable("id") Integer id, Model model) {
        ProblemVO problemVO = problemService.getProblem(id);
        model.addAttribute("problemVO", problemVO);
        return "problem/view";
    }

    @RequestMapping(value = "/problem/add", method = RequestMethod.GET)
    public String problemAdd() {
        return "problem/add";
    }

    @RequestMapping(value = "/problem/addok", method = RequestMethod.POST)
    public String problemAddOK(@ModelAttribute ProblemVO vo, HttpServletRequest request) {
        System.out.println("Received VO: " + vo); // 디버그용 로그

        if (vo.getTitle() == null || vo.getTitle().isEmpty()) {
            System.out.println("Title is null or empty!");
            return "redirect:problem/add?error=title_missing";
        }

        try {
            // 업로드된 파일 처리
            MultipartFile file = vo.getFile();
            if (file != null && !file.isEmpty()) {
                String savedFileName = saveFileWithRenamePolicy(file, request); // 파일 저장
                vo.setFilePath(savedFileName); // 저장된 경로를 VO에 설정
            }

            int i = problemService.insertProblem(vo);
            if (i == 0) {
                System.out.println("데이터 추가 실패!");
            } else {
                System.out.println("데이터 추가 성공!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:problem/add?error=upload_failed";
        }

        return "redirect:list";
    }

    @RequestMapping(value = "/problem/edit/{id}", method = RequestMethod.GET)
    public String problemEdit(@PathVariable("id") Integer id, Model model) {
        ProblemVO problemVO = problemService.getProblem(id);
        model.addAttribute("problemVO", problemVO);
        return "problem/edit";
    }

    @RequestMapping(value = "/problem/editok", method = RequestMethod.POST)
    public String problemEditOK(@ModelAttribute ProblemVO vo, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            if (vo.getFilePath() == null || vo.getFilePath().isEmpty()) {
                vo.setFilePath("default/path"); // 기본값 설정
            }

            // 기존 파일 삭제 및 새 파일 저장
            deleteFile(vo.getFilePath());
            String savedFileName = saveFileWithRenamePolicy(file, request); // 파일 저장
            vo.setFilePath(savedFileName); // 저장된 경로를 VO에 설정

            int i = problemService.updateProblem(vo);
            if (i == 0) {
                System.out.println("데이터 수정 실패!");
                return "redirect:/problem/edit/" + vo.getId() + "?error=update_failed";
            } else {
                System.out.println("데이터 수정 성공!");
                System.out.println("Saved File Path: " + savedFileName);
                System.out.println("VO File Path Before Update: " + vo.getFilePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/problem/edit/" + vo.getId() + "?error=upload_failed";
        }
        return "redirect:/problem/list";
    }

    @RequestMapping(value = "/problem/delete/{id}", method = RequestMethod.GET)
    public String problemDelete(@PathVariable("id") Integer id) {
        ProblemVO problemVO = problemService.getProblem(id);
        if (problemVO != null) {
            deleteFile(problemVO.getFilePath()); // 파일 삭제
            problemService.deleteProblem(id);
        }
        return "redirect:/problem/list";
    }

    @GetMapping("/problem/download/{id}")
    public void downloadFile(@PathVariable("id") Integer id, HttpServletResponse response) {
        ProblemVO problem = problemService.getProblem(id);
        if (problem == null || problem.getFilePath() == null) {
            throw new IllegalArgumentException("파일을 찾을 수 없습니다.");
        }

        File file = new File(UPLOAD_DIR, problem.getFilePath());
        if (!file.exists()) {
            throw new IllegalArgumentException("파일이 존재하지 않습니다.");
        }

        try (InputStream in = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {

            // 다운로드 헤더 설정
            response.setContentType(Files.probeContentType(Paths.get(file.getAbsolutePath())));
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(file.getName(), "UTF-8") + "\"");
            response.setContentLength((int) file.length());

            // 파일 데이터 복사
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 파일 저장 처리
    private String saveFileWithRenamePolicy(MultipartFile file, HttpServletRequest request) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("빈 파일입니다.");
        }

        String uploadDirPath = request.getServletContext().getRealPath("/WEB-INF/upload");
        File uploadDir = new File(uploadDirPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 파일 저장 경로와 원본 파일 이름 설정
        String originalFileName = file.getOriginalFilename();
        File destinationFile = new File(uploadDir, originalFileName);

        // 파일 이름 중복 처리
        DefaultFileRenamePolicy renamePolicy = new DefaultFileRenamePolicy();
        File renamedFile = renamePolicy.rename(destinationFile);

        // 파일 저장
        file.transferTo(renamedFile);

        return renamedFile.getName(); // 저장된 파일 이름 반환
    }

    // 파일 삭제 처리
    private void deleteFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return;
        }
        File file = new File(UPLOAD_DIR, fileName);
        if (file.exists()) {
            file.delete();
        }
    }
}

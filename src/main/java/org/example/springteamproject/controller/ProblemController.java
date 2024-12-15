package org.example.springteamproject.controller;

import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.example.springteamproject.dao.URLDAO;
import org.example.springteamproject.service.ProblemService;
import org.example.springteamproject.vo.ProblemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class ProblemController {

    @Autowired
    ProblemService problemService;

    @Autowired
    private URLDAO urldao;

    @RequestMapping("/")
    public String home() {
        return "home/index";
    }

    @RequestMapping(value = "/problem/list", method = RequestMethod.GET)
    public String problemList(@RequestParam(value = "searchKeyword", required = false) String searchKeyword, Model model, HttpSession session) {
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            model.addAttribute("list", problemService.searchProblems(searchKeyword));
        } else {
            model.addAttribute("list", problemService.getProblemList());
        }

        model.addAttribute("totalcnt", problemService.getTotalCnt());
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("userInfo", session.getAttribute("login"));

        return "problem/list";
    }

    @RequestMapping("/problem/view/{id}")
    public String problemView(@PathVariable("id") Integer id, Model model, HttpServletRequest request) throws IOException {
        ProblemVO problemVO = problemService.getProblem(id);
        if (problemVO == null) {
            throw new IllegalArgumentException("문제를 찾을 수 없습니다.");
        }

        model.addAttribute("problemVO", problemVO);

        String fileContent = "";
        if (problemVO.getFilePath() != null) {
            String filePath = getUploadDir(request) + "/" + problemVO.getFilePath();
            StringBuilder contentBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    contentBuilder.append(line).append("\n");
                }
            } catch (IOException e) {
                System.out.println("파일 읽기 실패: " + e.getMessage());
            }
            fileContent = contentBuilder.toString();
        }

        String language = problemVO.getLanguage() != null ? problemVO.getLanguage().toLowerCase() : "plaintext";

        model.addAttribute("codeContent", fileContent);
        model.addAttribute("language", language);

        String url = urldao.getURL(language);

        model.addAttribute("url", url);

        return "problem/view";
    }

    @RequestMapping(value = "/problem/add", method = RequestMethod.GET)
    public String problemAdd() {
        return "problem/add";
    }

    @RequestMapping(value = "/problem/addok", method = RequestMethod.POST)
    public String problemAddOK(@ModelAttribute ProblemVO vo, HttpServletRequest request) {
        if (vo.getTitle() == null || vo.getTitle().isEmpty()) {
            return "redirect:problem/add?error=title_missing";
        }

        try {
            MultipartFile file = vo.getFile();
            if (file != null && !file.isEmpty()) {
                String savedFileName = saveFileWithRenamePolicy(file, request);
                vo.setFilePath(savedFileName);
            }

            int i = problemService.insertProblem(vo);
            if (i == 0) {
                return "redirect:problem/add?error=insert_failed";
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
                vo.setFilePath("default/path");
            }

            deleteFile(vo.getFilePath(), request);
            String savedFileName = saveFileWithRenamePolicy(file, request);
            vo.setFilePath(savedFileName);

            int i = problemService.updateProblem(vo);
            if (i == 0) {
                return "redirect:/problem/edit/" + vo.getId() + "?error=update_failed";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/problem/edit/" + vo.getId() + "?error=upload_failed";
        }
        return "redirect:/problem/list";
    }

    @RequestMapping(value = "/problem/delete/{id}", method = RequestMethod.GET)
    public String problemDelete(@PathVariable("id") Integer id, HttpServletRequest request) {
        ProblemVO problemVO = problemService.getProblem(id);
        if (problemVO != null) {
            deleteFile(problemVO.getFilePath(), request);
            problemService.deleteProblem(id);
        }
        return "redirect:/problem/list";
    }

    @GetMapping("/problem/download/{id}")
    public void downloadFile(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        ProblemVO problem = problemService.getProblem(id);
        if (problem == null || problem.getFilePath() == null) {
            throw new IllegalArgumentException("파일을 찾을 수 없습니다.");
        }

        String uploadDirPath = getUploadDir(request);
        File file = new File(uploadDirPath, problem.getFilePath());
        if (!file.exists()) {
            throw new IllegalArgumentException("파일이 존재하지 않습니다.");
        }

        try (InputStream in = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {

            response.setContentType(Files.probeContentType(Paths.get(file.getAbsolutePath())));
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(file.getName(), "UTF-8") + "\"");
            response.setContentLength((int) file.length());

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to get upload directory path
    private String getUploadDir(HttpServletRequest request) {
        return request.getServletContext().getRealPath("/WEB-INF/upload");
    }

    private String saveFileWithRenamePolicy(MultipartFile file, HttpServletRequest request) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("빈 파일입니다.");
        }

        String uploadDirPath = getUploadDir(request);
        File uploadDir = new File(uploadDirPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String originalFileName = file.getOriginalFilename();
        File destinationFile = new File(uploadDir, originalFileName);

        DefaultFileRenamePolicy renamePolicy = new DefaultFileRenamePolicy();
        File renamedFile = renamePolicy.rename(destinationFile);

        file.transferTo(renamedFile);

        return renamedFile.getName();
    }

    private void deleteFile(String fileName, HttpServletRequest request) {
        if (fileName == null || fileName.isEmpty()) {
            return;
        }
        String uploadDirPath = getUploadDir(request);
        File file = new File(uploadDirPath, fileName);
        if (file.exists()) {
            file.delete();
        }
    }
}

package org.example.springteamproject.controller;

import org.example.springteamproject.dao.MemberDAO;
import org.example.springteamproject.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private MemberDAO memberDAO;

    @RequestMapping("/login")
    public String loginForm() {
        return "login/login";
    }

    @RequestMapping(value="/loginOK", method = RequestMethod.POST)
    public String loginOK(HttpServletRequest request, HttpSession session, Model model) {
        String returnURL = "";

        if (session.getAttribute("login")!=null) {
           session.removeAttribute("login");
        }

        // id is -1 because constuctor needs it, but it hasn't been assigned by the database yet
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        MemberVO formData = new MemberVO(-1, username, password);
        boolean isAuthenticated = memberDAO.checkUserPassword(formData);

        if (isAuthenticated) {
            System.out.println("Login Success");
            session.setAttribute("login", formData);
            session.setMaxInactiveInterval(600); // make session invalid after 10 min
            returnURL = "redirect:problem/list";
        } else {
            System.out.println("Login failed!");
            returnURL = "redirect:/login";
        }

        return returnURL;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:problem/list";
    }
}

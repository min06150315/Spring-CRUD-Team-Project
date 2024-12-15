package org.example.springteamproject.util;

import org.example.springteamproject.dao.MemberDAO;
import org.example.springteamproject.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle method called!");
        HttpSession session = request.getSession();
        MemberVO sessionAttr = (MemberVO)session.getAttribute("login");
        if (sessionAttr == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }
}

package com.creasy.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject = SecurityUtils.getSubject();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        AuthenticationToken authenticationToken = new UsernamePasswordToken(username, password);
        try {
            subject.login(authenticationToken);
        } catch (AuthenticationException e){
            resp.setContentType("text/plain;charset=UTF-8");
            resp.getWriter().println("AuthenticationException" + e.getMessage());
            return;
        }
        req.getRequestDispatcher("/success.jsp").forward(req, resp);
//        super.doPost(req, resp);
    }
}

package com.creasy.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/list")
public class UserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject = SecurityUtils.getSubject();
        resp.setContentType("text/plain;charset=UTF-8");
        if(subject.hasRole("admin")){//如果用Spring，可以通过注解实现
            resp.getWriter().println("用户列表--》");
        } else {
            resp.getWriter().println("您无权查看用户列表");
        }
    }
}

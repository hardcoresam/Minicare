package com.minicare.controller;

import com.minicare.form.LoginForm;
import com.minicare.model.Member;
import com.minicare.service.MemberService;
import com.minicare.util.MemberConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(MemberConstants.EMAIL);
        String password = request.getParameter(MemberConstants.PASSWORD);
        LoginForm loginForm = new LoginForm(email, password);

        HashMap<String,String> map = loginForm.validate();

        if(map.isEmpty()) {
            MemberService regService = new MemberService();
            Member member = regService.fetchMember(loginForm);
            if(member != null) {

                HttpSession session = request.getSession();
                session.setAttribute("member",member);

                request.setAttribute("firstName",member.getFirstName());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("HomePage"+ (member.getType().toString()).toLowerCase() +".jsp");
                requestDispatcher.forward(request, response);
            }
            else {
                request.setAttribute("loginError","Invalid Credentials - Please Try Again.");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
                requestDispatcher.forward(request, response);
            }
        }
        else {
            request.setAttribute("errors",map);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
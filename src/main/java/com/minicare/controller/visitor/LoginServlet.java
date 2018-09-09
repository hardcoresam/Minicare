package com.minicare.controller.visitor;

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


    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {


        //Think about doGet() Methods in every servlet


        doPost(request,response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(MemberConstants.EMAIL);
        String password = request.getParameter(MemberConstants.PASSWORD);
        LoginForm loginForm = new LoginForm(email, password);


        //So check if user is already logged in here also.
        //bcoz lets say the user opened a Login.jsp in two tabs and in one tab log in
        //now in the other tab we can still log in which should not be allowed.
        //So ask and check here also.


        HashMap<String,String> map = loginForm.validate();

        if(map.isEmpty()) {
            MemberService regService = new MemberService();
            Member member = regService.fetchMember(loginForm);

            if(member != null) {
                HttpSession session = request.getSession();
                session.setAttribute("member",member);

                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/"+  (member.getType().toString()).toLowerCase() +"/HomePage"+ (member.getType().toString()).toLowerCase() +".jsp");
                requestDispatcher.forward(request, response);
            }
            else {
                request.setAttribute("loginError","Invalid Credentials - Please Try Again.");
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/visitor/Login.jsp");
                requestDispatcher.forward(request, response);
            }
        }
        else {
            request.setAttribute("errors",map);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/visitor/Login.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
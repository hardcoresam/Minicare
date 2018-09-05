package com.minicare.controller;

import com.minicare.model.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");
        if (member == null) {
            request.setAttribute("loginError", "Please Login First");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp ");
            dispatcher.forward(request, response);
        }
        else {
            session.invalidate();
            request.setAttribute("loginError","You Have Successfully Logged Out.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request, response);
        }
    }
}

//Use the same page for the Logout in Sitter also.
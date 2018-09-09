package com.minicare.controller.member;

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

        session.invalidate();
        request.setAttribute("loginError","You Have Successfully Logged Out.");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/visitor/Login.jsp");
        dispatcher.forward(request, response);
    }
}
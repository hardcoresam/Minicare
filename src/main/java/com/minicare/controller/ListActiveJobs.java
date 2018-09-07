package com.minicare.controller;

import com.minicare.model.Job;
import com.minicare.model.Member;
import com.minicare.service.SitterService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListActiveJobs extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // From here Session checking Logic. ////////
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("member");
        if (member == null) {
            request.setAttribute("loginError", "Please Login First");

            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request, response);
        }
        // Uptil here ///////////
        else {
            SitterService sitterService = new SitterService();

            List<Job> list = sitterService.listActiveJobs(member.getMemberId());
            if(list.isEmpty()) {
                request.setAttribute("success","You have applied to all the jobs.");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("HomePagesitter.jsp");
                requestDispatcher.forward(request, response);
            }
            else {
                request.setAttribute("listOfActiveJobs",list);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("ListActiveJobs.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }
}
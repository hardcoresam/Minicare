package com.minicare.controller;

import com.minicare.model.Job;
import com.minicare.model.Member;
import com.minicare.service.SeekerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListJobServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // From here Session checking Logic. ////////
        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");
        if(member == null) {

            request.setAttribute("loginError","Please Login First");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request,response);
        }
        // Uptil here ///////////
        else {
            SeekerService seekerService = new SeekerService();
            List<Job> list = seekerService.listJobs(member.getMemberId());
            if(list.isEmpty()) {
                //Say to the user that You have not Posted any Job yet.
                //Exception Handling Here
            }
            else {
                request.setAttribute("listOfJobs",list);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("ListJobs.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }
}
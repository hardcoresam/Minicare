package com.minicare.controller;

import com.minicare.dto.ListApplicationDTO;
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

public class ListApplicationSeeker extends HttpServlet {
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
            int jobId = Integer.parseInt(request.getParameter("jobId"));
            SeekerService seekerService = new SeekerService();
            List<ListApplicationDTO> list = seekerService.listApplications(jobId);
            if(list.isEmpty()) {
                request.setAttribute("successMsg","There are no applications for this job yet.");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("ListJobs.do");
                requestDispatcher.forward(request, response);

                /*
                request.setAttribute("success","There are no applications for this job yet.");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("HomePageseeker.jsp");
                requestDispatcher.forward(request, response);
                */
            }
            else {
                request.setAttribute("listOfApplications",list);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("ListApplicationSeeker.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }
}
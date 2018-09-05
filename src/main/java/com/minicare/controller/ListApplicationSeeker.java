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
            //Ask whether this checking should be done here or in the PostJob.jsp itself?

            request.setAttribute("loginError", "Please Login First");
            //So while creating Login jsp Page, include this stmnt in the starting so that user will understand.
            // <c:out value="${param.loginError}"/>

            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request, response);
        }
        // Uptil here ///////////
        else {
            int jobId = Integer.parseInt(request.getParameter("jobId"));
            SeekerService seekerService = new SeekerService();
            List<ListApplicationDTO> list = seekerService.listApplications(jobId);
            if(list.isEmpty()) {
                //Say to the user that There are no applications for this job yet.
                //Exception Handling Here
            }
            else {
                request.setAttribute("listOfApplications",list);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("ListApplicationSeeker.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }
}

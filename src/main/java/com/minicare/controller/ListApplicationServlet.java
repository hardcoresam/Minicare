package com.minicare.controller;

import com.minicare.dto.JobApplicationDTO;
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

public class ListApplicationServlet extends HttpServlet {
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
            SitterService sitterService = new SitterService();
            List<JobApplicationDTO> list = sitterService.listApplications(member.getMemberId());
            if (list.isEmpty()) {
                //Say to the user that you have not applied to any job yet.
                //Exception Handling Here
            } else {
                request.setAttribute("listOfApplications", list);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("ListApplications.jsp");  //Create this.
                requestDispatcher.forward(request, response);
            }
        }
    }
}
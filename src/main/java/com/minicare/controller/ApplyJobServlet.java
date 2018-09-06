package com.minicare.controller;

import com.minicare.form.ApplyJobForm;
import com.minicare.model.Member;
import com.minicare.service.SitterService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class ApplyJobServlet extends HttpServlet {
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
            String jobId = request.getParameter("jobId");
            String expectedPay = request.getParameter("expectedPay");

            System.err.println(jobId);
            System.err.println(expectedPay);

            ApplyJobForm form = new ApplyJobForm(expectedPay, Integer.parseInt(jobId), member.getMemberId());
            String error = form.validate();

            if(error == null) {
                SitterService sitterService = new SitterService();
                boolean status = sitterService.applyJob(form);
                if(status == true) {
                    request.setAttribute("success","You have applied for the job Successfully");  //Think how to display this to user in
                                                                                                        //List Applications page.
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("ListApplication.do");
                    requestDispatcher.forward(request, response);
                }
                else {
                    //Exception Handling Here
                }
            }
            else {
                request.setAttribute("expectedPay",expectedPay);
                request.setAttribute("errors",error);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("ApplyJob.jsp");
                requestDispatcher.forward(request, response);
            }


        }
    }
}
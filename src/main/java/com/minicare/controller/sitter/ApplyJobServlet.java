package com.minicare.controller.sitter;

import com.minicare.form.ApplyJobForm;
import com.minicare.model.Member;
import com.minicare.service.SitterService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApplyJobServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Member member = (Member)request.getSession().getAttribute("member");

        String jobId = request.getParameter("jobId");
        String expectedPay = request.getParameter("expectedPay");

        ApplyJobForm form = new ApplyJobForm(expectedPay, Integer.parseInt(jobId), member.getMemberId());
        String error = form.validate();

        if(error == null) {
            SitterService sitterService = new SitterService();
            boolean status = sitterService.applyJob(form);
            if(status == true) {
                request.setAttribute("success","You have applied for the job Successfully");  //Think how to display this to user in
                                                                                                        //List Applications page.
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/sitter/listApplication.do");
                requestDispatcher.forward(request, response);
            }
            else {
                //Exception Handling Here
            }
        }
        else {
            request.setAttribute("expectedPay",expectedPay);
            request.setAttribute("errors",error);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/sitter/ApplyJob.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
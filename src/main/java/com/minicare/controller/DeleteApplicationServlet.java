package com.minicare.controller;

import com.minicare.model.Member;
import com.minicare.service.SitterService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteApplicationServlet extends HttpServlet {
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
            int applicationId = Integer.parseInt(request.getParameter("applicationId"));
            SitterService sitterService = new SitterService();
            if(sitterService.deleteApplication(applicationId)) {
                request.setAttribute("success","Application Was Deleted Successfully");
                RequestDispatcher dispatcher = request.getRequestDispatcher("ListApplication.do");
                dispatcher.forward(request,response);
            }
            else {
                //Redirect to an error page.
            }
        }
    }
}
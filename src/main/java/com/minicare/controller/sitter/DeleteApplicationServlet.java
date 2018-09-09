package com.minicare.controller.sitter;

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
        int applicationId = Integer.parseInt(request.getParameter("applicationId"));
        SitterService sitterService = new SitterService();
        if(sitterService.deleteApplication(applicationId)) {
            request.setAttribute("success","Application Was Deleted Successfully");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/sitter/listApplication.do");
            dispatcher.forward(request,response);
        }
        else {
            //Redirect to an error page.
        }
    }
}
package com.minicare.controller.sitter;

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
        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");

        SitterService sitterService = new SitterService();
        List<JobApplicationDTO> list = sitterService.listApplications(member.getMemberId());
        if (list.isEmpty()) {
            request.setAttribute("success","You have not applied to any Job yet.");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/sitter/HomePagesitter.jsp");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("listOfApplications", list);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/sitter/ListApplications.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
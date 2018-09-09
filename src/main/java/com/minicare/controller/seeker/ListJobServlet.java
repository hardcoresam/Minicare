package com.minicare.controller.seeker;

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
        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");

        SeekerService seekerService = new SeekerService();
        List<Job> list = seekerService.listJobs(member.getMemberId());
        if(list.isEmpty()) {
            request.setAttribute("success","There are no jobs which you have posted yet.");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/seeker/HomePageseeker.jsp");
            requestDispatcher.forward(request, response);
        }
        else {
            //This code is for - when we have no applications, we would forward to this page only.So for that, we use this.
            String successMsg = (String)request.getAttribute("successMsg");
            request.setAttribute("successMsg",successMsg);

            request.setAttribute("listOfJobs",list);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/seeker/ListJobs.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}

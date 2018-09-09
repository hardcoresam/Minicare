package com.minicare.controller.seeker;

import com.minicare.model.Member;
import com.minicare.service.SeekerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteJobServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int jobId = Integer.parseInt(request.getParameter("jobId"));
        SeekerService seekerService = new SeekerService();
        if(seekerService.deleteJob(jobId)) {
            request.setAttribute("successMsg","Job Was Deleted Successfully");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/seeker/listJobs.do");
            dispatcher.forward(request,response);
        }
        else {
            //Redirect to an error page.
        }
    }
}
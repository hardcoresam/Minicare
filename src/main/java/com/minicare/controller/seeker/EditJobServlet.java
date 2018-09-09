package com.minicare.controller.seeker;

import com.minicare.form.PostJobForm;
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

public class EditJobServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int jobId = Integer.parseInt(request.getParameter("jobId"));
        SeekerService seekerService = new SeekerService();
        Job job = seekerService.getJobById(jobId);

        request.setAttribute("form",job);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/seeker/EditJob.jsp");
        dispatcher.forward(request, response);
    }
}
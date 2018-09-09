package com.minicare.controller.seeker;

import com.minicare.form.PostJobForm;
import com.minicare.model.Member;
import com.minicare.service.SeekerService;
import com.minicare.util.MemberConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class PostJobServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");

        String title = request.getParameter(MemberConstants.TITLE);
        String payPerHour = request.getParameter(MemberConstants.PAY_PER_HOUR);
        String startTime = request.getParameter(MemberConstants.START_TIME);
        String endTime = request.getParameter(MemberConstants.END_TIME);
        String startDate = request.getParameter(MemberConstants.START_DATE);
        String endDate = request.getParameter(MemberConstants.END_DATE);

        PostJobForm form = new PostJobForm(title, payPerHour, startTime, endTime, startDate, endDate);
        HashMap<String,String> map = form.validate();

        if(map.isEmpty()) {
            SeekerService seekerService = new SeekerService();
            if(seekerService.postJob(form,member.getMemberId())) {
                request.setAttribute("success","Job Was Posted Successfully");
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/seeker/HomePageseeker.jsp");
                requestDispatcher.forward(request, response);
            }
            else {
                /*
                Ask how to display exceptions to user . Maybe an error page?
                 */
            }
        }
        else {
            request.setAttribute("form",form);
            request.setAttribute("errors",map);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/seeker/PostJob.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
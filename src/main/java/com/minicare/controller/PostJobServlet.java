package com.minicare.controller;

import com.minicare.form.PostJobForm;
import com.minicare.model.Member;
import com.minicare.service.RegistrationService;
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
    //May be take all the code of Checking whether user is logged in or not and keep it in another static method in util class
    //and you can use that here.
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");
        if(member == null) {
            //Ask whether this checking should be done here or in the PostJob.jsp itself?

            request.setAttribute("loginError","Please Login First");
            //So while creating Login jsp Page, include this stmnt in the starting so that user will understand.
            // <c:out value="${param.loginError}"/>

            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp ");
            dispatcher.forward(request,response);
        }

        //if the above if Condition executes then directly forwards kada, So check whether we can write all the below
        //code without else block or not.

        else {
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
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("HomePageseeker.jsp");  //Some Success Page Here
                    requestDispatcher.forward(request, response);
                }
                else {
                /*
                Ask how to display exceptions to user . Maybe an error page?
                 */
                }
            }
            else {
                request.setAttribute("errors",map);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("PostJob.jsp");
                requestDispatcher.forward(request, response);
            }

        }
    }
}

package com.minicare.controller.member;

import com.minicare.model.Member;
import com.minicare.service.SeekerService;
import com.minicare.service.SitterService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CloseAccountServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");


        //Here There is code duplicacy. after checking the type just call the respective service methods and write
        //the remaining logic after the if else loop. i.e from line 29 to line 32.


        if(member.getType() == Member.MemberType.SEEKER) {
            SeekerService seekerService = new SeekerService();
            if(seekerService.closeAccount(member.getMemberId())) {
                session.invalidate();
                request.setAttribute("loginError","You Have Successfully Closed Your Account");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/visitor/Login.jsp");
                dispatcher.forward(request, response);
            }
            else {
                //Exception Handling Here
            }
        }
        else {
            SitterService sitterService = new SitterService();
            if(sitterService.closeAccount(member.getMemberId())) {
                session.invalidate();
                request.setAttribute("loginError","You Have Successfully Closed Your Account");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/visitor/Login.jsp");
                dispatcher.forward(request, response);
            }
            else {
                //Exception Handling Here
            }
        }
    }
}
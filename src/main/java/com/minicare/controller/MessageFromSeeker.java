package com.minicare.controller;

import com.minicare.model.Member;
import com.minicare.model.Message;
import com.minicare.service.SeekerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MessageFromSeeker extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // From here Session checking Logic. ////////
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("member");
        if (member == null) {
            request.setAttribute("loginError", "Please Login First");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request, response);
        }
        // Uptil here ///////////
        else {
            int seekerId,sitterId;
            if(member.getType().toString().equalsIgnoreCase("seeker")) {
                seekerId = member.getMemberId();
                sitterId = Integer.parseInt(request.getParameter("sitterId"));
            }
            else {
                sitterId = member.getMemberId();
                seekerId = Integer.parseInt(request.getParameter("seekerId"));
            }

            SeekerService seekerService = new SeekerService();
            int conversationId = seekerService.getConversationId(seekerId, sitterId);

            request.setAttribute("conversationId",conversationId);
            RequestDispatcher dispatcher = request.getRequestDispatcher("GetMessages.do");
            dispatcher.forward(request, response);
        }
    }
}

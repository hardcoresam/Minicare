package com.minicare.controller.member;

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

public class SendMessageServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");

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
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/member/getMessages.do");
        dispatcher.forward(request, response);
    }
}

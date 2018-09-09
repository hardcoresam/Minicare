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
import java.util.List;

public class GetMessages extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int conversationId = (Integer)request.getAttribute("conversationId");
        SeekerService seekerService = new SeekerService();
        List<Message> list = seekerService.getMessages(conversationId);

        if(list.isEmpty()) {
            request.setAttribute("conversationId",conversationId);
        }

        request.setAttribute("listOfMessages",list);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/member/ListOfMessages.jsp");
        requestDispatcher.forward(request, response);
    }
}

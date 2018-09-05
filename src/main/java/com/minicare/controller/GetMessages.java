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
import java.util.List;

public class GetMessages extends HttpServlet {
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

            int conversationId = (Integer)request.getAttribute("conversationId");
            SeekerService seekerService = new SeekerService();
            List<Message> list = seekerService.getMessages(conversationId);

            if(list.isEmpty()) {
                request.setAttribute("conversationId",conversationId);
            }

            request.setAttribute("listOfMessages",list);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("ListOfMessages.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}

package com.minicare.controller;

import com.minicare.model.Member;
import com.minicare.service.SeekerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StoreMessageServlet extends HttpServlet {
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
            String content = request.getParameter("message");
            if(content.equals("")) {

                //but if you do validation like this after this gets redirected to the ListOfMessages.jsp - then the previous messages
                //will be gone .bcoz we are not passing List<Message> to the jsp here in this case.
                //So think of another way for validation for this.

                request.setAttribute("messageError", "Please enter something to send");
                RequestDispatcher dispatcher = request.getRequestDispatcher("ListOfMessages.jsp");
                dispatcher.forward(request, response);
            }
            else {
                int conversationId = Integer.parseInt(request.getParameter("conversationId"));
                SeekerService seekerService = new SeekerService();
                if(seekerService.storeMessage(conversationId,content,member.getMemberId())) {
                    request.setAttribute("conversationId", conversationId);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("GetMessages.do");
                    dispatcher.forward(request, response);
                }
                else {
                    //Exception Handling i.e redirect to an error page
                }
            }
        }
    }
}

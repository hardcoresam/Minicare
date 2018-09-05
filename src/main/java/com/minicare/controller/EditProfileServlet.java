package com.minicare.controller;

import com.minicare.model.Member;
import com.minicare.model.Seeker;
import com.minicare.model.Sitter;
import com.minicare.service.SeekerService;
import com.minicare.service.SitterService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditProfileServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");
        if (member == null) {
            request.setAttribute("loginError", "Please Login First");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request, response);
        }
        else {
            if(member.getType().toString().equalsIgnoreCase("seeker")) {
                SeekerService service = new SeekerService();
                Seeker seeker = service.fetchMember(member.getMemberId());

                seeker.setFirstName(member.getFirstName());
                seeker.setLastName(member.getLastName());
                seeker.setEmail(member.getEmail());
                seeker.setPhoneNumber(member.getPhoneNumber());
                seeker.setPassword(member.getPassword());
                seeker.setAddress(member.getAddress());
                seeker.setType(member.getType());

                request.setAttribute("member",seeker);

                RequestDispatcher dispatcher = request.getRequestDispatcher("EditProfile.jsp");
                dispatcher.forward(request, response);
            }
            else {
                SitterService service = new SitterService();
                Sitter sitter = service.fetchMember(member.getMemberId());

                sitter.setFirstName(member.getFirstName());
                sitter.setLastName(member.getLastName());
                sitter.setEmail(member.getEmail());
                sitter.setPhoneNumber(member.getPhoneNumber());
                sitter.setPassword(member.getPassword());
                sitter.setAddress(member.getAddress());
                sitter.setType(member.getType());

                request.setAttribute("member",sitter);
                RequestDispatcher dispatcher = request.getRequestDispatcher("EditProfile.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}

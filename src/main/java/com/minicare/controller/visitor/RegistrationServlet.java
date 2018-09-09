package com.minicare.controller.visitor;

import com.minicare.form.RegistrationForm;
import com.minicare.model.Member;
import com.minicare.service.MemberService;
import com.minicare.util.MemberConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class RegistrationServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter(MemberConstants.FIRST_NAME);
        String lastName = request.getParameter(MemberConstants.LAST_NAME);
        String email = request.getParameter(MemberConstants.EMAIL);
        String password = request.getParameter(MemberConstants.PASSWORD);
        String phoneNumber = request.getParameter(MemberConstants.PHONE_NUMBER);
        String address = request.getParameter(MemberConstants.ADDRESS);
        String type = request.getParameter(MemberConstants.TYPE);
        String noOfChildren,spouseName,experience,expectedPay;
        noOfChildren=spouseName=experience=expectedPay="";

        if(type.equals("seeker")) {
            noOfChildren = request.getParameter(MemberConstants.NO_OF_CHILDREN);
            spouseName = request.getParameter(MemberConstants.SPOUSE_NAME);
        }
        else {
            experience = request.getParameter(MemberConstants.EXPERIENCE);
            expectedPay = request.getParameter(MemberConstants.EXPECTED_PAY);
        }

        RegistrationForm regForm = new RegistrationForm(firstName, lastName, phoneNumber, email, password, address,
                                                        noOfChildren, spouseName, experience, expectedPay, type);

        HashMap<String,String> map = regForm.validate();

        if(map.isEmpty()) {
            MemberService memberService = new MemberService();
            Member member = memberService.registerUser(regForm);
            if(member != null) {
                HttpSession session = request.getSession();
                session.setAttribute("member",member);
                request.setAttribute("firstName",member.getFirstName());

                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/"+ type.toLowerCase() +"/HomePage"+ type.toLowerCase() +".jsp");
                requestDispatcher.forward(request, response);
            }
            else {
                /*
                Ask how to display exceptions to user . Maybe an error page?
                 */
            }
        }
        else {
            request.setAttribute("form",regForm);
            request.setAttribute("errors",map);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/visitor/Registration.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
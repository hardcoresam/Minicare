package com.minicare.controller;

import com.minicare.form.AlterProfileForm;
import com.minicare.model.Member;
import com.minicare.service.RegistrationService;
import com.minicare.util.MemberConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class AlterProfileServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter(MemberConstants.FIRST_NAME);
        String lastName = request.getParameter(MemberConstants.LAST_NAME);
        String phoneNumber = request.getParameter(MemberConstants.PHONE_NUMBER);
        String address = request.getParameter(MemberConstants.ADDRESS);
        String type = request.getParameter(MemberConstants.TYPE);  //This is a hidden field in the previous page.

        String noOfChildren, spouseName, experience, expectedPay;
        noOfChildren = spouseName = experience = expectedPay = "";

        if (type.equalsIgnoreCase("seeker")) {   //If not working, convert to upper case and try
            noOfChildren = request.getParameter(MemberConstants.NO_OF_CHILDREN);
            System.err.println(noOfChildren);
            spouseName = request.getParameter(MemberConstants.SPOUSE_NAME);
        }
        else {
            experience = request.getParameter(MemberConstants.EXPERIENCE);
            expectedPay = request.getParameter(MemberConstants.EXPECTED_PAY);
        }

        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");

        //ASK WHETHER WE SHOULD WRITE SESSION LOGIC HERE ALSO OR NOT.

        AlterProfileForm alterForm = new AlterProfileForm(member.getMemberId(), firstName, lastName, phoneNumber, address, noOfChildren, spouseName, experience, expectedPay, type);

        HashMap<String,String> map = alterForm.validate();



        if(map.isEmpty()) {
            RegistrationService regService = new RegistrationService();
            Member updatedMember = regService.alterProfile(alterForm);

            //ASK IF THIS APPROACH IS CORRECT OR NOT.
            session.invalidate();
            HttpSession updatedSession = request.getSession();
            updatedSession.setAttribute("member",updatedMember);

            request.setAttribute("success","Your Profile Was Updated Successfully");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("HomePage"+ type.toLowerCase() +".jsp");
            requestDispatcher.forward(request, response);

        }
        else {
            request.setAttribute("errors",map);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditProfile.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}

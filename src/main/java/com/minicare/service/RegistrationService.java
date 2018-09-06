package com.minicare.service;

import com.minicare.dao.MemberDAO;
import com.minicare.form.AlterProfileForm;
import com.minicare.form.LoginForm;
import com.minicare.form.RegistrationForm;
import com.minicare.model.Member;
import com.minicare.model.Seeker;
import com.minicare.model.Sitter;

public class RegistrationService {

    public Member registerUser(RegistrationForm form) {
        int memberId = -1;
        Seeker seeker = null;
        Sitter sitter = null;
        if(form.getType().equals("seeker")) {
            //Changing no of children and spouse name to null fields if empty.
            if(form.getNoOfChildren().equals(""))
                //form.setNoOfChildren(null);
            if(form.getSpouseName().equals("")) {
                //form.setSpouseName(null);
            }
            //Check the above code..

            seeker = new Seeker(form.getFirstName(), form.getLastName(), form.getEmail(), form.getPhoneNumber(),
                    form.getAddress(), form.getPassword(), Integer.parseInt(form.getNoOfChildren()), form.getSpouseName());

            MemberDAO memberDao = new MemberDAO();
            memberId = memberDao.registerUser(seeker);
            seeker.setMemberId(memberId);
            return seeker;
        }
        else {
            sitter = new Sitter(form.getFirstName(), form.getLastName(), form.getEmail(),
                    form.getPhoneNumber(), form.getAddress(), form.getPassword(), Integer.parseInt(form.getExperience()),
                    Float.parseFloat(form.getExpectedPay()));

            MemberDAO memberDao = new MemberDAO();
            memberId = memberDao.registerUser(sitter);
            sitter.setMemberId(memberId);
            return sitter;
        }
    }

    public boolean isEmailRegistered(String email) {
        MemberDAO memberDao = new MemberDAO();
        return memberDao.isEmailRegistered(email);
    }

    public Member fetchMember(LoginForm loginForm) {
        MemberDAO memberDao = new MemberDAO();
        Member member = memberDao.fetchMember(loginForm);
        return member;
    }

    public Member alterProfile(AlterProfileForm alterForm) {
        Member member = null;
        System.err.println(alterForm);
        if(alterForm.getType().equalsIgnoreCase("seeker")) {

            //Changing no of children and spouse name to null fields if empty.
            if(alterForm.getNoOfChildren().equals("")) {
                //form.setNoOfChildren(null);
            }
            if(alterForm.getSpouseName().equals("")) {
                //form.setSpouseName(null);
            }
            //Check the above code..

            Seeker seeker = new Seeker();
            seeker.setMemberId(alterForm.getMemberId());
            seeker.setFirstName(alterForm.getFirstName());
            seeker.setLastName(alterForm.getLastName());
            seeker.setPhoneNumber(alterForm.getPhoneNumber());
            seeker.setAddress(alterForm.getAddress());
            seeker.setNoOfChildren(Integer.parseInt(alterForm.getNoOfChildren()));
            seeker.setSpouseName(alterForm.getSpouseName());
            seeker.setType(Member.MemberType.SEEKER);

            MemberDAO memberDao = new MemberDAO();
            member = memberDao.alterUser(seeker);
        }
        else {
            Sitter sitter = new Sitter();
            sitter.setMemberId(alterForm.getMemberId());
            sitter.setFirstName(alterForm.getFirstName());
            sitter.setLastName(alterForm.getLastName());
            sitter.setPhoneNumber(alterForm.getPhoneNumber());
            sitter.setAddress(alterForm.getAddress());
            sitter.setExperience(Integer.parseInt(alterForm.getExperience()));
            sitter.setExpectedPay(Float.parseFloat(alterForm.getExpectedPay()));
            sitter.setType(Member.MemberType.SITTER);

            MemberDAO memberDao = new MemberDAO();
            member = memberDao.alterUser(sitter);
        }
        return member;
    }
}

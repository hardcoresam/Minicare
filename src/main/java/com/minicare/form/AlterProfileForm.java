package com.minicare.form;

import java.util.HashMap;

public class AlterProfileForm {
    private int memberId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String noOfChildren;
    private String spouseName;
    private String experience;
    private String expectedPay;
    private String type;

    public AlterProfileForm(int memberId, String firstName, String lastName, String phoneNumber, String address, String noOfChildren,
                            String spouseName, String experience, String expectedPay, String type) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.noOfChildren = noOfChildren;
        this.spouseName = spouseName;
        this.experience = experience;
        this.expectedPay = expectedPay;
        this.type = type;
    }

    public int getMemberId() { return memberId; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getNoOfChildren() {
        return noOfChildren;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public String getExperience() {
        return experience;
    }

    public String getExpectedPay() {
        return expectedPay;
    }

    public String getType() {
        return type;
    }

    public HashMap<String,String> validate() {

        /*
        Code Validation Logic Here like this.

        if(username.length()==0)
            map.put("username","Please Enter Username");
        if(password.length()==0)
            map.put("password","Please Enter Password");
        if(type.equals("Seeker"))
        {
            Do validation here for No of Children and Spouse Name)
        }
        else
        {
            Do validation here for experience and expected pay.
        }
         */

        /*
        Also we need to check if the user already exists in the database.
        if yes then we need to say that to the user.
         */

        //You can reuse the code. bcoz same validation as RegistrationForm - So REUSE code.

        return new HashMap<String, String>();

    }
}

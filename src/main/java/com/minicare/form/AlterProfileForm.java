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

        HashMap<String,String> map = new HashMap<>();

        //FIRSTNAME
        if(firstName.equals(""))
            map.put("firstName","Please Enter Firstname");
        else if(!firstName.matches("^[a-zA-Z]+$"))
            map.put("firstName","Firstname must only have characters");

        //LASTNAME
        if(!lastName.matches("^[a-zA-Z]*$")) {
            map.put("lastName","Lastname must only have characters");
        }

        //PHONE NUMBER
        if(phoneNumber.equals(""))
            map.put("phoneNumber","Please enter a Phone Number");
        else if(!phoneNumber.matches("^[0-9]{10}$"))
            map.put("phoneNumber","Please enter Valid Phone Number");

        //ADDRESS
        if(address.equals(""))
            map.put("address","Please enter your address");

        if(type.equalsIgnoreCase("Seeker"))
        {
            //No OF CHILDREN
            if(noOfChildren.equals(""))
                map.put("noOfChildren","Please Enter no of children");
            else if(!noOfChildren.matches("^0$|^[1-9][0-9]{0,9}$"))
                map.put("noOfChildren","No of Children must contain only numbers with no preceding zeroes");

            //SPOUSE NAME
            if(!spouseName.equals("")) {
                if(!spouseName.matches("^[a-zA-Z]+([a-zA-Z ]*[a-zA-Z]+)*$"))
                    map.put("spouseName","Spouse Name must contain only characters");
            }
        }
        else
        {
            //EXPERIENCE
            if(experience.equals(""))
                map.put("experience","Please enter Experience");
            else if(!experience.matches("^0$|^[1-9][0-9]{0,9}$"))
                map.put("experience","Experience must contain only numbers with no preceding zeroes");

            //EXPECTED PAY
            if(expectedPay.equals(""))
                map.put("expectedPay","Please enter Expected Pay");
            else if(!expectedPay.matches("^0$|^[1-9]+([\\.]?[0-9]+)?$"))
                map.put("expectedPay","Expected Pay must contain only digits");
        }

        return map;
    }
}
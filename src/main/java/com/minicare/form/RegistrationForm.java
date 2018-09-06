package com.minicare.form;

import com.minicare.service.RegistrationService;

import java.util.HashMap;

public class RegistrationForm {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private String address;
    private String noOfChildren;
    private String spouseName;
    private String experience;
    private String expectedPay;
    private String type;

    public RegistrationForm(String firstName, String lastName, String phoneNumber, String email, String password,
                            String address, String noOfChildren, String spouseName, String experience,
                            String expectedPay, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.address = address;
        this.noOfChildren = noOfChildren;
        this.spouseName = spouseName;
        this.experience = experience;
        this.expectedPay = expectedPay;
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getNoOfChildren() {
        return noOfChildren;
    }

    public String getSpouseName() { return spouseName; }

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
        //CHANGE THE NAME OF MAP TO ERRORS OR ERROR.
        HashMap<String,String> map = new HashMap<>();

        //Ask if i should keep each validation in a seperate method?

        //FIRSTNAME
        if(firstName.equals(""))
            map.put("firstName","Please Enter Firstname");
        else if(!firstName.matches("^[a-zA-Z]+$"))
            map.put("firstName","Firstname must only have characters");

        //LASTNAME
        if(!lastName.matches("^[a-zA-Z]*$")) {
            map.put("lastName","Lastname must only have characters");
        }

        //EMAIL
        if(email.equals(""))
            map.put("email","Please enter Email");
        else if(!email.matches("^[a-zA-Z0-9]{1}([a-zA-Z0-9.-_*]*[a-zA-Z0-9]+)*@[a-zA-Z0-9]{1}([a-zA-Z0-9.-_*]*[a-zA-Z0-9]+)*$"))
            map.put("email","Please enter a valid Email");
        else {
            //Check this.
            RegistrationService registrationService = new RegistrationService();
            if(registrationService.isEmailRegistered(email))
                map.put("email", "An Account already exists with given EmailId");
        }

        //PHONE NUMBER
        if(phoneNumber.equals(""))
            map.put("phoneNumber","Please enter a Phone Number");
        else if(!phoneNumber.matches("^[0-9]{10}$"))
            map.put("phoneNumber","Phone Number must contain only 10 digits");

        //ADDRESS
        if(address.equals(""))
            map.put("address","Please enter your address");

        //PASSWORD
        if(password.equals(""))
            map.put("password","Please enter password");
        else if(password.matches("^[a-zA-Z0-9\\W]{6,20}$"))
            map.put("password","Please enter minimum 6 characters");

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

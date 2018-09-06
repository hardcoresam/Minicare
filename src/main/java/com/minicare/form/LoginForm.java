package com.minicare.form;

import java.util.HashMap;

public class LoginForm {

    private String email;
    private String password;

    public LoginForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public HashMap<String,String> validate() {
        HashMap<String,String> map = new HashMap<>();

        //EMAIL
        if(email.equals(""))
            map.put("email","Please enter Email");
        else if(!email.matches("^[a-zA-Z0-9]{1}([a-zA-Z0-9.-_*]*[a-zA-Z0-9]+)*@[a-zA-Z0-9]{1}([a-zA-Z0-9.-_*]*[a-zA-Z0-9]+)*$"))
            map.put("email","Please enter a valid Email");

        //PASSWORD
        if(password.equals(""))
            map.put("password","Please enter password");

        return map;
    }
}

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

        //Think of someway to merge this with RegistrationForm. bcoz same methods we will be using for checking username and password.

        //Do validation here.
        /*
        Also we need to check if the user already exists in the database.
        if yes then we need to say that to the user.

        Also if the user is there in the database already but his status is Inactive, then What should we do? - ASK
         */

        return new HashMap<String, String>();

    }
}

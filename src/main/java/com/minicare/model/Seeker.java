package com.minicare.model;

public class Seeker extends Member {
    private int noOfChildren;
    private String spouseName;

    public Seeker() {}

    public Seeker(String firstName, String lastName, String email, String phoneNumber, String address,
                  String password, int noOfChildren, String spouseName) {
        super(firstName, lastName, email, phoneNumber, address, password, MemberType.SEEKER);
        this.noOfChildren = noOfChildren;    //This will be an empty String if we dont pass anything in the form page.
                                            // if we dont pass anything then null should be stored here not empty string.
        this.spouseName = spouseName;
    }



    public int getNoOfChildren() {
        return noOfChildren;
    }

    public void setNoOfChildren(int noOfChildren) {
        this.noOfChildren = noOfChildren;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }
}

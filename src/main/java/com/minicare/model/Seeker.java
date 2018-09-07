package com.minicare.model;

public class Seeker extends Member {
    private int noOfChildren;
    private String spouseName;

    public Seeker() {}

    public Seeker(String firstName, String lastName, String email, String phoneNumber, String address,
                  String password, int noOfChildren, String spouseName) {
        super(firstName, lastName, email, phoneNumber, address, password, MemberType.SEEKER);
        this.noOfChildren = noOfChildren;
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

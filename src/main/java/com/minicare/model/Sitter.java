package com.minicare.model;

public class Sitter extends Member {
    private int experience;
    private float expectedPay;

    public Sitter() {}

    public Sitter(String firstName, String lastName, String email, String phoneNumber, String address,
                  String password, int experience, float expectedPay) {
        super(firstName, lastName, email, phoneNumber, address, password, MemberType.SITTER);
        this.experience = experience;
        this.expectedPay = expectedPay;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public float getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(float expectedPay) {
        this.expectedPay = expectedPay;
    }
}

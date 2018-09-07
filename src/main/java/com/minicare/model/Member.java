package com.minicare.model;

public class Member {
    private int memberId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;

    public enum MemberType{SEEKER,SITTER};
    public enum Status{ACTIVE,INACTIVE};

    private MemberType type;
    private Status status;

    public Member() {}
    public Member(String firstName, String lastName, String email, String phoneNumber, String address,
                  String password, MemberType type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.type=type;
        this.status = Status.ACTIVE;    //Is this correct? Think while retrieving rows from database.
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MemberType getType() { return type; }

    public void setType(MemberType type) { this.type = type; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }
}

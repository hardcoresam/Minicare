package com.minicare.dto;

public class ListApplicationDTO {
    private String title;
    private String firstName;
    public enum Status{ACTIVE,INACTIVE};
    private Status status;
    private double expectedPay;
    private int sitterId;

    public ListApplicationDTO() {}

    public ListApplicationDTO(String title, String firstName, Status status, double expectedPay, int sitterId) {
        this.title = title;
        this.firstName = firstName;
        this.status = status;
        this.expectedPay = expectedPay;
        this.sitterId = sitterId;
    }

    public int getSitterId() { return sitterId; }

    public void setSitterId(int sitterId) { this.sitterId = sitterId; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) { this.title = title; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) { this.status = status; }

    public double getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(double expectedPay) {
        this.expectedPay = expectedPay;
    }
}

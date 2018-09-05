package com.minicare.dto;

public class JobApplicationDTO {
    private String title;
    private double payPerHour;
    private double expectedPay;
    private int jobId;
    private int applicationId;
    public enum Status{ACTIVE,INACTIVE};
    private Status status;
    private int seekerId;

    public JobApplicationDTO() {}

    public JobApplicationDTO(String title, double payPerHour, double expectedPay, int jobId, int applicationId, Status status, int seekerId) {
        this.title = title;
        this.payPerHour = payPerHour;
        this.expectedPay = expectedPay;
        this.jobId = jobId;
        this.applicationId = applicationId;
        this.status = status;
        this.seekerId = seekerId;
    }

    public int getSeekerId() { return seekerId; }

    public void setSeekerId(int seekerId) { this.seekerId = seekerId; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPayPerHour() {
        return payPerHour;
    }

    public void setPayPerHour(double payPerHour) {
        this.payPerHour = payPerHour;
    }

    public double getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(double expectedPay) {
        this.expectedPay = expectedPay;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

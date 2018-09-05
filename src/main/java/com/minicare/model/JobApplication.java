package com.minicare.model;

public class JobApplication {
    private int applicationId;
    private int jobId;
    private int sitterId;
    private float expectedPay;

    public JobApplication(int jobId, int sitterId, float expectedPay) {
        this.jobId = jobId;
        this.sitterId = sitterId;
        this.expectedPay = expectedPay;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getSitterId() {
        return sitterId;
    }

    public void setSitterId(int sitterId) {
        this.sitterId = sitterId;
    }

    public float getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(float expectedPay) {
        this.expectedPay = expectedPay;
    }
}

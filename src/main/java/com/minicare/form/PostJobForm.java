package com.minicare.form;

import java.util.HashMap;

public class PostJobForm {
    private int jobId;
    private String title;
    private String payPerHour;
    private String startTime;
    private String endTime;
    private String startDate;
    private String endDate;

    public PostJobForm(String title, String payPerHour, String startTime, String endTime, String startDate, String endDate) {
        this.title = title;
        this.payPerHour = payPerHour;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getPayPerHour() {
        return payPerHour;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setJobId(int jobId) { this.jobId = jobId; }

    public int getJobId() { return jobId; }

    public HashMap<String,String> validate() {

        //Do Job Validation Here

        return new HashMap<String, String>();
    }
}

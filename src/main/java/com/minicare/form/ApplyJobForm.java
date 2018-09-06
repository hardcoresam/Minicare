package com.minicare.form;

import java.util.HashMap;

public class ApplyJobForm {
    private String expectedPay;
    private int jobId;
    private int sitterId;

    public ApplyJobForm(String expectedPay, int jobId, int sitterId) {
        this.expectedPay = expectedPay;
        this.jobId = jobId;
        this.sitterId = sitterId;
    }

    public String getExpectedPay() {
        return expectedPay;
    }

    public int getJobId() {
        return jobId;
    }

    public int getSitterId() {
        return sitterId;
    }

    public String validate() {
        String error = null;

        if(expectedPay.equals(""))
            error = "Please enter Expected Pay";
        else if(!expectedPay.matches("^[0-9]+([\\.]?[0-9]+)?$"))
            error = "Please enter a valid number";

        return error;
    }
}

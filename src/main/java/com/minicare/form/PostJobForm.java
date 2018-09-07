package com.minicare.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.sql.Date;

public class PostJobForm {
    private int jobId;
    private String title;
    private String payPerHour;
    private String startTime;
    private String endTime;
    private String startDate;
    private String endDate;
    private Date startDate1;
    private Date endDate1;

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
        startDate1 = null;
        endDate1 = null;

        HashMap<String,String> map = new HashMap<>();

        if(title.equals(""))
            map.put("title","Please enter Title");
        else if(!title.matches("^[a-zA-Z]+([a-zA-Z ]*[a-zA-Z]+)*$"))
            map.put("title","Title should contain only characters");

        if(payPerHour.equals(""))
            map.put("payPerHour","Please enter Pay Per Hour");
        else if(!payPerHour.matches("^0$|^[1-9]+([\\.]?[0-9]+)?$"))
            map.put("payPerHour","Please enter a valid Number");


        if(startDate.equals(""))
            map.put("startDate","Please Enter Start Date");
        else {
            try {
                startDate1 = Date.valueOf(startDate);
            } catch (IllegalArgumentException e) {
                map.put("startDate", "Please enter Valid date in the Correct Format");
            }

            //Checking startDate Should be either today or after today's date.
            if(startDate1 != null) {
                java.util.Date date=new java.util.Date();
                if(!startDate1.after(date)) {
                    map.put("startDate","Start Date should be greater than Today's date");
                }
            }
        }


        if(endDate.equals(""))
            map.put("endDate","Please Enter End Date");
        else {
            try {
                endDate1 = Date.valueOf(endDate);
            } catch (IllegalArgumentException e) {
                map.put("endDate", "Please enter Valid date in the Correct Format");   //Show the format in the placeholder
            }

            //If start Date is not null and also if end date is not null
            if(startDate1 != null && endDate1 != null) {
                if(!endDate1.after(startDate1)){
                    map.put("endDate","End Date should not be before Start Date");
                }
            }
        }

        if(startTime.equals(""))
            map.put("startTime","Please enter Start Time");
        else {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");   //Show the format in the placeholder
                java.util.Date d1 = simpleDateFormat.parse(startTime);
            }
            catch(ParseException p) {
                map.put("startTime","Please enter valid time in the Correct Format");
            }
        }

        if(endTime.equals(""))
            map.put("endTime","Please enter End Time");
        else {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");   //Show the format in the placeholder
                java.util.Date d2 = simpleDateFormat.parse(endTime);
            }
            catch(ParseException p) {
                map.put("endTime","Please enter valid time in the Correct Format");
            }
        }
        return map;
    }
}

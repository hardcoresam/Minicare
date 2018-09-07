package com.minicare.service;

import com.minicare.dao.*;
import com.minicare.dto.ListApplicationDTO;
import com.minicare.form.PostJobForm;
import com.minicare.model.Job;
import com.minicare.model.JobApplication;
import com.minicare.model.Message;
import com.minicare.model.Seeker;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class SeekerService {
    public boolean postJob(PostJobForm form, int seekerId) {
        Job job = new Job(form.getTitle(), Double.parseDouble(form.getPayPerHour()), seekerId, Time.valueOf(form.getStartTime()+":00"), Time.valueOf(form.getEndTime()+":00"), Date.valueOf(form.getStartDate()), Date.valueOf(form.getEndDate()));

        JobDAO jobDao = new JobDAO();
        return jobDao.postJob(job);
    }

    public boolean alterJob(PostJobForm form) {
        String startTime = form.getStartTime();
        String endTime = form.getEndTime();

        if(startTime.length()==5) {
            startTime = startTime +":00";
        }
        if(endTime.length()==5) {
            endTime = endTime +":00";
        }

        Job job = new Job(form.getTitle(), Double.parseDouble(form.getPayPerHour()), Time.valueOf(startTime), Time.valueOf(endTime), Date.valueOf(form.getStartDate()), Date.valueOf(form.getEndDate()));
        job.setJobId(form.getJobId());

        JobDAO jobDao = new JobDAO();
        return jobDao.editJob(job);
    }

    public List<Job> listJobs(int seekerId) {
        JobDAO jobDao = new JobDAO();
        List<Job> list = jobDao.listJobs(seekerId);
        return list;
    }

    public Seeker fetchMember(int seekerId) {
        SeekerDAO seekerDao = new SeekerDAO();
        Seeker seeker = seekerDao.getSeekerById(seekerId);
        return seeker;
    }

    public boolean deleteJob(int jobId) {
        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        if(jobApplicationDao.deleteApplicationsById(jobId)) {
            JobDAO jobDao = new JobDAO();
            return jobDao.deleteJob(jobId);
        }
        else {
            return false;
        }
    }

    public Job getJobById(int jobId) {
        JobDAO jobDao = new JobDAO();
        Job job = jobDao.getJobById(jobId);
        return job;
    }

    public boolean closeAccount(int seekerId) {
        //also ask pranav about updating 2 tables in one operation - like the one above.
        //Check whether we can make the Closing account(both sitter and seeker) operations in any other efficient way.

        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        JobDAO jobDao = new JobDAO();
        List<Job> list = jobDao.listJobs(seekerId);
        for(Job job : list) {
            jobApplicationDao.deleteApplicationsById(job.getJobId());
            jobDao.deleteJob(job.getJobId());
        }

        MemberDAO memberDao = new MemberDAO();
        return memberDao.closeAccount(seekerId);
    }

    public List<ListApplicationDTO> listApplications(int jobId) {
        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        List<ListApplicationDTO> list = jobApplicationDao.listApplicationsForSeeker(jobId);
        return list;
    }

    public int getConversationId(int seekerId, int sitterId) {
        ConversationDAO conversationDao = new ConversationDAO();
        return conversationDao.getConversationId(seekerId, sitterId);
    }

    public List<Message> getMessages(int conversationId) {
        ConversationDAO conversationDao = new ConversationDAO();
        return conversationDao.getMessages(conversationId);
    }

    public boolean storeMessage(int conversationId, String content, int senderId) {
        ConversationDAO conversationDao = new ConversationDAO();
        return conversationDao.storeMessage(conversationId,content,senderId);
    }
}

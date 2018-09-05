package com.minicare.service;

import com.minicare.dao.JobApplicationDAO;
import com.minicare.dao.JobDAO;
import com.minicare.dao.MemberDAO;
import com.minicare.dao.SitterDAO;
import com.minicare.dto.JobApplicationDTO;
import com.minicare.form.ApplyJobForm;
import com.minicare.model.Job;
import com.minicare.model.JobApplication;
import com.minicare.model.Sitter;

import java.util.List;

public class SitterService {
    public Sitter fetchMember(int sitterId) {
        SitterDAO sitterDao = new SitterDAO();
        Sitter sitter = sitterDao.getSitterById(sitterId);
        return sitter;
    }

    public List<Job> listActiveJobs(int sitterId) {
        JobDAO jobDao = new JobDAO();
        List<Job> list = jobDao.listActiveJobs(sitterId);
        return list;
    }

    public boolean applyJob(ApplyJobForm form) {

        JobApplication jobApplication = new JobApplication(form.getJobId(), form.getSitterId(), Float.parseFloat(form.getExpectedPay()));

        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        return jobApplicationDao.applyJob(jobApplication);
    }

    public List<JobApplicationDTO> listApplications(int sitterId) {
        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        List<JobApplicationDTO> list = jobApplicationDao.listApplications(sitterId);
        return list;
    }

    public boolean deleteApplication(int applicationId) {
        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        return jobApplicationDao.deleteApplication(applicationId);
    }

    public boolean closeAccount(int sitterId, String type) {
        MemberDAO memberDao = new MemberDAO();
        return memberDao.closeAccount(sitterId,type);
    }
}

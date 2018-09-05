package com.minicare.dao;

import com.minicare.dto.JobApplicationDTO;
import com.minicare.dto.ListApplicationDTO;
import com.minicare.form.ApplyJobForm;
import com.minicare.model.Job;
import com.minicare.model.JobApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationDAO {

    public static Connection getConnection() {
        Connection con=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/care","root","password");
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public boolean applyJob(JobApplication application) {
        boolean status = false;
        Connection con=null;
        try {
            con = getConnection();
            PreparedStatement pst = con.prepareStatement("insert into jobapplication (JobId, SitterId, ExpectedPay) values (?,?,?)");
            pst.setInt(1,application.getJobId());
            pst.setInt(2,application.getSitterId());
            pst.setFloat(3,application.getExpectedPay());
            if(pst.executeUpdate()>0) {
                status = true;
            }
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                System.out.println(e);
            }
        }
        return status;
    }

    public List<JobApplicationDTO> listApplications(int sitterId) {
        List<JobApplicationDTO> list = new ArrayList<JobApplicationDTO>();
        Connection con=null;
        try {
            con = getConnection();
            PreparedStatement pst = con.prepareStatement("select job.Title, jobapplication.ExpectedPay, job.PayPerHour, job.Status, " +
                    "job.JobId, jobapplication.ApplicationId, job.SeekerId from job inner join jobapplication on job.JobId=jobapplication.JobId where " +
                    "jobapplication.SitterId=? and jobapplication.Status=?");
            pst.setInt(1,sitterId);
            pst.setString(2,"ACTIVE");
            ResultSet resultSet = pst.executeQuery();

            while(resultSet.next()) {
                JobApplicationDTO jobApplicationDto = new JobApplicationDTO();
                jobApplicationDto.setTitle(resultSet.getString(1));
                jobApplicationDto.setExpectedPay(resultSet.getDouble(2));
                jobApplicationDto.setPayPerHour(resultSet.getDouble(3));
                jobApplicationDto.setStatus(JobApplicationDTO.Status.valueOf(resultSet.getString(4)));
                jobApplicationDto.setJobId(resultSet.getInt(5));
                jobApplicationDto.setApplicationId(resultSet.getInt(6));
                jobApplicationDto.setSeekerId(resultSet.getInt(7));

                list.add(jobApplicationDto);
            }

        }
        catch(SQLException e) {
            System.out.println(e);
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                System.out.println(e);
            }
        }
        return list;
    }

    public boolean deleteApplication(int applicationId) {
        Connection con=null;
        boolean status = false;
        try {
            con = getConnection();
            PreparedStatement pst = con.prepareStatement("update jobapplication set Status = ? where ApplicationId = ?");
            pst.setString(1,"INACTIVE");
            pst.setInt(2,applicationId);
            if(pst.executeUpdate()>0) {
                status = true;
            }
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                System.out.println(e);
            }
        }
        return status;
    }

    public boolean deleteApplicationsById(int jobId) {
        Connection con=null;
        boolean status = false;
        try {
            con = getConnection();
            PreparedStatement pst = con.prepareStatement("update jobapplication set Status = ? where JobId = ?");
            pst.setString(1,"INACTIVE");
            pst.setInt(2,jobId);
            if(pst.executeUpdate()>0) {
                status = true;
            }
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                System.out.println(e);
            }
        }
        return status;
    }

    public List<ListApplicationDTO> listApplicationsForSeeker(int jobId) {
        List<ListApplicationDTO> list = new ArrayList<ListApplicationDTO>();
        Connection con=null;
        try {
            con = getConnection();
            PreparedStatement pst = con.prepareStatement("select member.FirstName, jobapplication.Status, jobapplication.ExpectedPay, " +
                    "job.Title, jobapplication.SitterId from jobapplication inner join job on jobapplication.JobId=job.JobId inner join member " +
                    "on jobapplication.SitterId=member.MemberId where jobapplication.JobId=?");
            pst.setInt(1,jobId);
            ResultSet resultSet = pst.executeQuery();

            while(resultSet.next()) {
                ListApplicationDTO listApplication = new ListApplicationDTO();
                listApplication.setFirstName(resultSet.getString(1));
                listApplication.setStatus(ListApplicationDTO.Status.valueOf(resultSet.getString(2)));
                listApplication.setExpectedPay(resultSet.getDouble(3));
                listApplication.setTitle(resultSet.getString(4));
                listApplication.setSitterId(resultSet.getInt(5));

                list.add(listApplication);
            }
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                System.out.println(e);
            }
        }
        return list;
    }
}

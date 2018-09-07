package com.minicare.dao;

import com.minicare.model.Job;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.naming.NamingException;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class JobDAO {
    public boolean postJob(Job job) {
        boolean status = false;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into job (Title, PayPerHour, SeekerId, StartDate, EndDate, StartTime, EndTime) values (?,?,?,?,?,?,?)");
            pst.setString(1,job.getTitle());
            pst.setDouble(2,job.getPayPerHour());
            pst.setInt(3,job.getSeekerId());
            pst.setDate(4,job.getStartDate());
            pst.setDate(5,job.getEndDate());
            pst.setTime(6,job.getStartTime());
            pst.setTime(7,job.getEndTime());
            if(pst.executeUpdate()>0) {
                status = true;
            }
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return status;
    }

    public boolean editJob(Job job) {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("update job set Title=?, PayPerHour=?, StartDate=?, EndDate=?, StartTime=?, " +
                    "EndTime=? where JobId=?");
            pst.setString(1,job.getTitle());
            pst.setDouble(2,job.getPayPerHour());
            pst.setDate(3,job.getStartDate());
            pst.setDate(4,job.getEndDate());
            pst.setTime(5,job.getStartTime());
            pst.setTime(6,job.getEndTime());
            pst.setInt(7,job.getJobId());
            pst.executeUpdate();
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return true;
    }

    public List<Job> listJobs(int seekerId) {
        List<Job> list = new ArrayList<Job>();
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("select Title, Status, StartDate, EndDate, StartTime, EndTime, JobId from job where SeekerId = ? and Status = ?");
            pst.setInt(1,seekerId);
            pst.setString(2,"ACTIVE");
            ResultSet res = pst.executeQuery();
            while(res.next()) {
                Job job = new Job();
                job.setTitle(res.getString(1));
                job.setStatus(Job.Status.valueOf(res.getString(2).toUpperCase()));
                job.setStartDate(res.getDate(3));
                job.setEndDate(res.getDate(4));
                job.setStartTime(res.getTime(5));
                job.setEndTime(res.getTime(6));
                job.setJobId(res.getInt(7));
                list.add(job);
            }
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean deleteJob(int jobId) {
        boolean status = false;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("update job set Status = ? where JobId = ?");
            pst.setString(1,"INACTIVE");
            pst.setInt(2,jobId);
            if(pst.executeUpdate()>0) {
                status = true;
            }
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return status;
    }

    public List<Job> listActiveJobs(int sitterId) {
        List<Job> list = new ArrayList<Job>();
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("select Title, PayPerHour, StartDate, EndDate, StartTime, EndTime, JobId " +
                    "from job where JobId not in (select JobId from jobapplication where SitterId=? and Status=?) and Status=?");
            pst.setInt(1,sitterId);
            pst.setString(2,"ACTIVE");
            pst.setString(3,"ACTIVE");
            ResultSet res = pst.executeQuery();

            while(res.next()) {
                Job job = new Job();
                job.setTitle(res.getString(1));
                job.setPayPerHour(res.getDouble(2));
                job.setStartDate(res.getDate(3));
                job.setEndDate(res.getDate(4));
                job.setStartTime(res.getTime(5));
                job.setEndTime(res.getTime(6));
                job.setJobId(res.getInt(7));

                list.add(job);
            }
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return list;
    }

    public Job getJobById(int jobId) {
        Job job = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from job where JobId=?");
            pst.setInt(1,jobId);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()) {
                job = new Job();
                job.setJobId(resultSet.getInt(1));
                job.setTitle(resultSet.getString(2));
                job.setPayPerHour(resultSet.getDouble(3));
                job.setStartDate(resultSet.getDate(6));
                job.setEndDate(resultSet.getDate(7));
                job.setStartTime(resultSet.getTime(8));
                job.setEndTime(resultSet.getTime(9));
            }
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return job;
    }
}
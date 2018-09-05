package com.minicare.dao;

import com.minicare.model.Seeker;

import java.sql.*;

public class SeekerDAO {
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

    public Seeker getSeekerById(int seekerId) {
        Seeker seeker = null;
        Connection con=null;
        try {
            con = getConnection();
            PreparedStatement pst = con.prepareStatement("select * from seeker where SeekerId=?");
            pst.setInt(1,seekerId);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()) {
                seeker = new Seeker();
                seeker.setNoOfChildren(resultSet.getInt(2));
                seeker.setSpouseName(resultSet.getString(3));
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
        return seeker;
    }
}

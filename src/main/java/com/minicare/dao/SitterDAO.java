package com.minicare.dao;

import com.minicare.model.Sitter;

import java.sql.*;

public class SitterDAO {
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

    public Sitter getSitterById(int sitterId) {
        Sitter sitter = null;
        Connection con=null;
        try {
            con = getConnection();
            PreparedStatement pst = con.prepareStatement("select * from sitter where SitterId=?");
            pst.setInt(1,sitterId);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()) {
                sitter = new Sitter();
                sitter.setExperience(resultSet.getInt(2));
                sitter.setExpectedPay(resultSet.getFloat(3));
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
        return sitter;
    }
}

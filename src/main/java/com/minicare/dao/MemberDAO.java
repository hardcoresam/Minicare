package com.minicare.dao;

import com.minicare.form.LoginForm;
import com.minicare.model.Member;
import com.minicare.model.Seeker;
import com.minicare.model.Sitter;

import java.sql.*;


public class MemberDAO {
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

    //Do Password Hashing.....
    //Chnage the variable Names .

    public boolean isEmailRegistered(String email) {
        boolean status = false;
        Connection con=null;
        try {
            con = getConnection();
            PreparedStatement pst = con.prepareStatement("select MemberId from member where Email=?");
            pst.setString(1,email);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()) {
                status = true;
            }
        }
        catch (SQLException e) {
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

    public int registerUser(Member member) {
        int status=0;
        int memberId = -1;
        Connection con=null;
        try {
            con = getConnection();
            PreparedStatement pst = con.prepareStatement("insert into member (FirstName, LastName, Email, PhoneNumber, Password, Address, Type) " +
                                                              "values(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, member.getFirstName());
            pst.setString(2, member.getLastName());
            pst.setString(3, member.getEmail());
            pst.setString(4, member.getPhoneNumber());
            pst.setString(5, member.getPassword());
            pst.setString(6, member.getAddress());
            pst.setString(7, member.getType().toString());
            status = pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                memberId = rs.getInt(1);
            }

            //Should we write if(status > 0) here

            if (member.getType() == Member.MemberType.SEEKER) {
                PreparedStatement pst2 = con.prepareStatement("insert into seeker (SeekerId, NoOfChildren, SpouseName) values(?,?,?)");
                pst2.setInt(1,memberId);
                pst2.setInt(2,((Seeker)member).getNoOfChildren());
                pst2.setString(3,((Seeker)member).getSpouseName());
                int status1 = pst2.executeUpdate();

            }
            else {
                PreparedStatement pst3 = con.prepareStatement("insert into sitter (SitterId, Experience, ExpectedPay) values(?,?,?)");
                pst3.setInt(1,memberId);
                pst3.setInt(2,((Sitter)member).getExperience());
                pst3.setFloat(3,((Sitter)member).getExpectedPay());
                int status1 = pst3.executeUpdate();

            }

        }
        catch (SQLException e) {
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
        return memberId;
    }

    public Member fetchMember(LoginForm loginForm) {
        Connection con=null;
        Member member=null;
        try {
            con = getConnection();
            PreparedStatement pst = con.prepareStatement("select * from member where Email = ? and Password = ? and Status = ?");
            pst.setString(1,loginForm.getEmail());
            pst.setString(2,loginForm.getPassword());
            pst.setString(3,"ACTIVE");
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()) {
                member = new Member();
                member.setMemberId(resultSet.getInt(1));
                member.setFirstName(resultSet.getString(2));
                member.setLastName(resultSet.getString(3));
                member.setEmail(resultSet.getString(4));
                member.setPhoneNumber(resultSet.getString(5));
                member.setPassword(resultSet.getString(6));
                member.setAddress(resultSet.getString(7));
                member.setType(Member.MemberType.valueOf(resultSet.getString(8)));
                member.setStatus(Member.Status.valueOf(resultSet.getString(9)));
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
        return member;
    }



    public Member alterUser(Member member) {
        int status = -1;
        int memberId = -1;
        Connection con=null;
        try {
            con = getConnection();
            PreparedStatement pst = con.prepareStatement("update member set FirstName=?, LastName=?, PhoneNumber=?, Address=? where " +
                    "MemberId=?");
            pst.setString(1, member.getFirstName());
            pst.setString(2, member.getLastName());
            pst.setString(3, member.getPhoneNumber());
            pst.setString(4, member.getAddress());
            pst.setInt(5, member.getMemberId());
            status = pst.executeUpdate();

            //Should we write if(status > 0) here

            if (member.getType() == Member.MemberType.SEEKER) {
                PreparedStatement pst2 = con.prepareStatement("update seeker set NoOfChildren=?, SpouseName=? where SeekerId=?");
                pst2.setInt(1,((Seeker)member).getNoOfChildren());
                pst2.setString(2,((Seeker)member).getSpouseName());
                pst2.setInt(3,member.getMemberId());
                int status1 = pst2.executeUpdate();
            }
            else {
                PreparedStatement pst3 = con.prepareStatement("update sitter set Experience=?, ExpectedPay=? where SitterId=?");
                pst3.setInt(1,((Sitter)member).getExperience());
                pst3.setFloat(2,((Sitter)member).getExpectedPay());
                pst3.setInt(3,member.getMemberId());
                int status1 = pst3.executeUpdate();
            }
        }
        catch (SQLException e) {
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
        return member;
    }

    public boolean closeAccount(int memberId, String type) {
        Connection con = null;
        boolean status = false;
        try {
            con = getConnection();
            if(type.equalsIgnoreCase("seeker")) {
                PreparedStatement pst1 = con.prepareStatement("update job set Status=? where SeekerId=?");
                pst1.setString(1,"INACTIVE");
                pst1.setInt(2,memberId);
                pst1.executeUpdate();
            }
            else {
                PreparedStatement pst2 = con.prepareStatement("update jobapplication set Status=? where SitterId=?");
                pst2.setString(1,"INACTIVE");
                pst2.setInt(2,memberId);
                pst2.executeUpdate();
            }
            PreparedStatement pst3 = con.prepareStatement("update member set Status=? where MemberId=?");
            pst3.setString(1,"INACTIVE");
            pst3.setInt(2,memberId);
            if(pst3.executeUpdate()>0) {
                status = true;
            }
        }
        catch (SQLException e) {
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

    /*
    public Member fetchMemberById(int memberId) {
        Connection con=null;
        Member member=null;
        try {
            con = getConnection();
            PreparedStatement pst = con.prepareStatement("select * from member where MemberId=?");
            pst.setInt(1,memberId);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()) {
                member = new Member();
                member.setMemberId(resultSet.getInt(1));
                member.setFirstName(resultSet.getString(2));
                member.setLastName(resultSet.getString(3));
                member.setEmail(resultSet.getString(4));
                member.setPhoneNumber(resultSet.getString(5));
                member.setPassword(resultSet.getString(6));
                member.setAddress(resultSet.getString(7));
                member.setType(Member.MemberType.valueOf(resultSet.getString(8)));
                member.setStatus(Member.Status.valueOf(resultSet.getString(9)));
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
        return member;
    }
    */
}

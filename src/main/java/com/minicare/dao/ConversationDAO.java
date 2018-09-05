package com.minicare.dao;

import com.minicare.model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConversationDAO {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/care", "root", "password");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public int getConversationId(int seekerId, int sitterId) {
        int conversationId = -1;
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement pst = con.prepareStatement("select ConversationId from conversation where SeekerId=? and SitterId=?");
            pst.setInt(1,seekerId);
            pst.setInt(2,sitterId);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()) {
                conversationId = resultSet.getInt(1);
            }
            else {
                PreparedStatement pst1 = con.prepareStatement("insert into conversation (SeekerId, SitterId) values (?,?)",Statement.RETURN_GENERATED_KEYS);
                pst1.setInt(1,seekerId);
                pst1.setInt(2,sitterId);
                pst1.executeUpdate();

                ResultSet rs = pst1.getGeneratedKeys();
                if (rs.next()) {
                    conversationId = rs.getInt(1);
                }
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
        return conversationId;
    }

    public List<Message> getMessages(int conversationId) {

        List<Message> list = new ArrayList<Message>();
        Connection con=null;
        try {
            con = getConnection();
            PreparedStatement pst = con.prepareStatement("select Content, ConversationId from message where ConversationId=?");
            pst.setInt(1,conversationId);
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()) {
                Message message = new Message();
                message.setContent(resultSet.getString(1));
                message.setConversationId(resultSet.getInt(2));

                list.add(message);
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

    public boolean storeMessage(int conversationId, String content) {
        boolean status = false;
        Connection con=null;
        try {
            con = getConnection();

            long currentTime = System.currentTimeMillis();
            Time time = new Time(currentTime);

            PreparedStatement pst = con.prepareStatement("insert into message (ConversationId, Content, Time) values (?,?,?)");
            pst.setInt(1,conversationId);
            pst.setString(2,content);
            pst.setTime(3,time);
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
}

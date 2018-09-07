package com.minicare.dao;

import com.minicare.model.Message;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.naming.NamingException;

import java.util.ArrayList;
import java.util.List;

public class ConversationDAO {
    public int getConversationId(int seekerId, int sitterId) {
        int conversationId = -1;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
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
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return conversationId;
    }

    public List<Message> getMessages(int conversationId) {

        List<Message> list = new ArrayList<Message>();
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();

            //Check this query

            PreparedStatement pst = con.prepareStatement("select message.Content, message.ConversationId, message.Time, member.FirstName " +
                    "from message inner join member on message.SenderId=member.MemberId where ConversationId=?");
            pst.setInt(1,conversationId);
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()) {
                Message message = new Message();
                message.setContent(resultSet.getString(1));
                message.setConversationId(resultSet.getInt(2));
                message.setTime(resultSet.getTime(3));
                message.setFirstName(resultSet.getString(4));

                list.add(message);
            }
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean storeMessage(int conversationId, String content, int senderId) {
        boolean status = false;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();

            long currentTime = System.currentTimeMillis();
            Time time = new Time(currentTime);

            PreparedStatement pst = con.prepareStatement("insert into message (ConversationId, Content, Time, SenderId) values (?,?,?,?)");
            pst.setInt(1,conversationId);
            pst.setString(2,content);
            pst.setTime(3,time);
            pst.setInt(4,senderId);
            if(pst.executeUpdate()>0) {
                status = true;
            }
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return status;
    }
}

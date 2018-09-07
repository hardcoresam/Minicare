package com.minicare.dao;

import com.minicare.model.Sitter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class SitterDAO {
    public Sitter getSitterById(int sitterId) {
        Sitter sitter = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from sitter where SitterId=?");
            pst.setInt(1,sitterId);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()) {
                sitter = new Sitter();
                sitter.setExperience(resultSet.getDouble(2));
                sitter.setExpectedPay(resultSet.getDouble(3));
            }
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return sitter;
    }
}

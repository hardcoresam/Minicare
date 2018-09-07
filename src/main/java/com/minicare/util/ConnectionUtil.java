package com.minicare.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection getConnection() throws NamingException, SQLException {
        Connection con = null;
        try{
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            con = ds.getConnection();
        }
        catch(NamingException|SQLException connectionException) {
            /*
            Log it or handle it
             */
        }
        return con;
    }
}

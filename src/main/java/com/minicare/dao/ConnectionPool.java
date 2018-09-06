package com.minicare.dao;

//import com.mchange.v2.c3p0.*;

import com.mysql.jdbc.Connection;

import java.sql.SQLException;

/*
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

public class ConnectionPool {

    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/care");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private ConnectionPool(){ }
}

//Connection con = DBCPDataSource.getConnection();

*/


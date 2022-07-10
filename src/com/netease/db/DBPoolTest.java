package com.netease.db;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class DBPoolTest {
    private static BasicDataSource ds = null;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://101.43.236.42:3306/cloud_study";
    static final String USER = "root";
    static final String PASSWORD = "Zdf0208..";

    public static void dbpoolInit() {
        ds = new BasicDataSource();
        ds.setDriverClassName(JDBC_DRIVER);
        ds.setUrl(DB_URL);
        ds.setUsername(USER);
        ds.setPassword(PASSWORD);
    }

    public static void dbpoolTest() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        // 2.建立数据库连接
        try {
            // DBPool重写了connection的close()方法，此处close()方法进行的操作是将该连接归还给连接池
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select username from user");
            while (rs.next()) {
                System.out.println("Hello " + rs.getString("username"));
            }
        } catch (SQLException e) {
            // 异常处理
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        dbpoolInit();
        dbpoolTest();
    }
}

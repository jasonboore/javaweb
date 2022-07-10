package com.netease.db;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class DBPoolImpl extends Thread {
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
        ds.setMaxTotal(2);
    }

    public void dbpoolTest() {
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

    public void jdbcTest() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        // 1.装载驱动程序
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 2.建立数据库连接
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            // 3、执行SQL语句
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select username from user");
            // 4、获取执行结果
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

    public void run() {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 10000) {
//            jdbcTest();
            dbpoolTest();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        dbpoolInit();
        for (int i = 0; i < 10; i++) {
            new DBPoolImpl().start();
        }
    }
}


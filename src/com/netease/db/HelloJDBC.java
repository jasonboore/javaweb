package com.netease.db;

import java.sql.*;

public class HelloJDBC {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://101.43.236.42:3306/cloud_study";
    static final String USER = "root";
    static final String PASSWORD = "Zdf0208..";

    public static void main(String[] args) throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        // 1.装载驱动程序
        Class.forName(JDBC_DRIVER);

        // 2.建立数据库连接
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            // 3、执行SQL语句
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select name from user");
            // 4、获取执行结果
            while (rs.next()) {
                System.out.println("Hello " + rs.getString("name"));
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
}

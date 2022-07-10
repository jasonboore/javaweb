package com.netease.db;

import java.sql.*;

/**
 * 使用游标的方式分批读取数据
 */
public class JDBCCursor {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static String DB_URL = "jdbc:mysql://101.43.236.42:3306/cloud_study";
    static final String USER = "root";
    static final String PASSWORD = "Zdf0208..";

    public static void main(String[] args) throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        // 1.装载驱动程序
        Class.forName(JDBC_DRIVER);

        // 2.建立数据库连接
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            DB_URL = DB_URL + "useCursorFetch = true";
            // 3、执行SQL语句
            String sql = "select username from user";
            ptmt = conn.prepareStatement(sql);
            ptmt.setFetchSize(2);
            rs = ptmt.executeQuery();
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
}

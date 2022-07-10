package com.netease.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 批量插入或更新数据，防止数据量过大多次访问数据库
 */
public class JDBCBatch {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static String DB_URL = "jdbc:mysql://101.43.236.42:3306/cloud_study";
    static final String USER = "root";
    static final String PASSWORD = "Zdf0208..";

    public static void insertUsers(List<String> list) throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        // 1.装载驱动程序
        Class.forName(JDBC_DRIVER);

        // 2.建立数据库连接
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            DB_URL = DB_URL + "useCursorFetch = true";
            // 3、执行SQL语句
            stmt = conn.createStatement();
            for (String user: list) {
                stmt.addBatch("insert into user(username) values('" + user + "')");
            }
            stmt.executeBatch();
            stmt.clearBatch();
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        List<String> list = new ArrayList<>();
        list.add("zhangsan");
        list.add("lisi");
        list.add("wangwu");
        insertUsers(list);
    }
}


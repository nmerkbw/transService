package com.jxs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by jiangxs on 2018/3/19.
 */
public class DBUtil {

    private static final String URL = "jdbc:mysql://127.0.0.1/mydb2";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;

    static {

        try {
            // 获得数据库的连接
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获得数据库连接失败！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("未找到该类！");
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}

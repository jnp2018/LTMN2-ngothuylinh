package com.util;

import java.sql.*;

public class ConnectDB {
    public static String driver = "com.mysql.jdbc.Driver";
    public static String hostName = "localhost";
    public static String port = "3306";
    public static String databaseName = "db_bai3?characterEncoding=UTF-8";
    public static String username = "root";
    public static String password = "";
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;


    public static Connection openConnect() throws SQLException {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            System.out.println("Error Connecton! Please check url or username and password of mysql!");
        }
        Connection conn = DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + port + "/" + databaseName + "", username, password);

        return conn;
    }


    public void closeConnect() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Please check close connect, stmt, resutlset");
        }
    }
}


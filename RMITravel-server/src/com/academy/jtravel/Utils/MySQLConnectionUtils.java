package com.academy.jtravel.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySQLConnectionUtils {
    private static String hostName = "127.0.0.1";
    private static int port = 3306;
    private static String userName = "root";
    private static String password = "password-here";
    private static String dbName = "rmitravel";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException{
        Class.forName(driverName);
        String connectionURL = generateConnectionURL(hostName, port, dbName);
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;

    }
    private static String generateConnectionURL(String hostName, int port, String dbName) {
        String connectionURL = "jdbc:mysql://" + hostName + ":" + port + "/" + dbName;
        return connectionURL;
    }
}

package com.bank.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    static {
        //Provided by your driver documentation. In this case, a MySql driver is used :
        String DRIVER_CLASS_NAME = "org.apache.derby.jdbc.ClientDriver";
        try {
            Class.forName(DRIVER_CLASS_NAME).newInstance();
        } catch (Exception ex) {
            System.out.println("Check classpath. Cannot load db driver: " + DRIVER_CLASS_NAME);
        }

    }

    public Connection getDBConnection() {
        //See your driver documentation for the proper format of this string :
        String DB_CONN_STRING = "jdbc:derby://localhost:1527/BankApplicationDB";

        String USERNAME = "root";
        String PASSWORD = "1234";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_CONN_STRING, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Driver loaded, but cannot connect to db: " + DB_CONN_STRING);
        }
        return connection;
    }

}

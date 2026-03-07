package com.addressBook.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
      private static final String url = "jdbc:mysql://localhost:3306/addressbook";
      private static final String user = "root";
      private static final String password = "051203";
      private static  Connection connection;
      private ConnectDatabase(Connection connection) {
    	  this.connection = connection;
      }
      
      public static Connection getConnection() throws SQLException {

          if (connection == null || connection.isClosed()) {
              connection = DriverManager.getConnection(url,user,password);
          }

          return connection;
      }
}

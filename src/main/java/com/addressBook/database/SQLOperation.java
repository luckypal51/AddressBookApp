package com.addressBook.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.addressBook.apps.model.Contacts;

public class SQLOperation {
      private static Connection con;
      
      public static void add (Contacts contacts,String addressBookName) {
    	  try {
    		  con = ConnectDatabase.getConnection();
    		  String url = "Insert into contacts(first_name,last_name,address,city,state,zip,phone_no,email,addressBookType) values(?,?,?,?,?,?,?,?,?)";
    		  
    		  PreparedStatement per = con.prepareStatement(url);
    		  per.setString(1,contacts.getFirstName());
    		  per.setString(2,contacts.getLastName());
    		  per.setString(3,contacts.getAddress());
    		  per.setString(4,contacts.getCity());
    		  per.setString(5,contacts.getState());
    		  per.setInt(6,contacts.getZip());
    		  per.setString(7, contacts.getPhoneNo());
    		  per.setString(8,contacts.getEmail());
    		  per.setString(9,addressBookName);
    		  int s = per.executeUpdate();
    		  
    	  }catch(Exception e) {
    		  System.out.println(e.getMessage());
    	  }
    	 
      }
      public static  List<Contacts> getAll(String s) throws SQLException {
    	  List<Contacts> list = new ArrayList<>();
    	  con = ConnectDatabase.getConnection();
    	  String url = "Select * from contacts where addressBooktype = ?";
    	  PreparedStatement st = con.prepareStatement(url);
    	  st.setString(1, s);
    	  ResultSet set = st.executeQuery();
    	  while(set.next()) {
    		  Contacts temp = new Contacts();
    		  temp.setFirstName(set.getString("first_name"));
    		  temp.setLastName(set.getString("last_name"));
    		  temp.setAddress(set.getString("address"));
    		  temp.setCity(set.getString("city"));
    		  temp.setState(set.getString("state"));
    		  temp.setZip(set.getInt("zip"));
    		  temp.setPhoneNo(set.getString("phone_no"));
    		  temp.setEmail(set.getString("email"));
    		  
    		  list.add(temp);
    	  }
    	  return list;
      }
      
      public static void UpdateDetailInDatabase(String name, Contacts contacts) {
    	  try {
    		  con = ConnectDatabase.getConnection();
    		  String query = "Update contacts set last_name = ? , address = ? , city = ? , state = ? , zip = ? ,phone_no = ? , email = ? where first_name = ?";
    		  PreparedStatement per = con.prepareStatement(query);
    		  per.setString(1,contacts.getLastName());
    		  per.setString(2,contacts.getAddress());
    		  per.setString(3,contacts.getCity());
    		  per.setString(4,contacts.getState());
    		  per.setInt(5,contacts.getZip());
    		  per.setString(6, contacts.getPhoneNo());
    		  per.setString(7,contacts.getEmail());
    		  per.setString(8, name);
    		  int set = per.executeUpdate();
    	  }catch(Exception e) {
    		  System.out.println(e.getMessage());
    	  }
      }
}

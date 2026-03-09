package com.addressBook.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.addressBook.apps.model.Contacts;

public class SQLOperation {
      private static Connection con;
      
      public static void add (Contacts contacts,String addressBookName) {
    	  if(!isPresent(addressBookName)) {
    		  createTable(addressBookName);
    	  }
    	  try {
    		  con = ConnectDatabase.getConnection();
    		  String url = "Insert into "+addressBookName+"(first_name,last_name,address,city,state,zip,phone_no,email,date) values(?,?,?,?,?,?,?,?,?)";
    		  
    		  PreparedStatement per = con.prepareStatement(url);
    		  per.setString(1,contacts.getFirstName());
    		  per.setString(2,contacts.getLastName());
    		  per.setString(3,contacts.getAddress());
    		  per.setString(4,contacts.getCity());
    		  per.setString(5,contacts.getState());
    		  per.setInt(6,contacts.getZip());
    		  per.setString(7, contacts.getPhoneNo());
    		  per.setString(8,contacts.getEmail());
    		  per.setDate(9,Date.valueOf(contacts.getDate()));
    		  int s = per.executeUpdate();
    		  
    	  }catch(Exception e) {
    		  System.out.println(e.getMessage());
    	  }
    	 
      }
      public static  List<Contacts> getAll(String s) throws SQLException {
    	  if(!isPresent(s)) return null;
    	  List<Contacts> list = new ArrayList<>();
    	  con = ConnectDatabase.getConnection();
    	  String url = "Select * from "+s;
    	  PreparedStatement st = con.prepareStatement(url);
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
    		  temp.setDate(LocalDate.parse(set.getDate("date").toString()));
    		  list.add(temp);
    	  }
    	  return list;
      }
      
      public static void UpdateDetailInDatabase(String addressBookName,String name, Contacts contacts) {
    	  if(!isPresent(addressBookName)) return;
    	  try {
    		  con = ConnectDatabase.getConnection();
    		  String query = "Update ? set last_name = ? , address = ? , city = ? , state = ? , zip = ? ,phone_no = ? , email = ? where first_name = ?";
    		  PreparedStatement per = con.prepareStatement(query);
    		  per.setString(1,addressBookName);
    		  per.setString(2,contacts.getLastName());
    		  per.setString(3,contacts.getAddress());
    		  per.setString(4,contacts.getCity());
    		  per.setString(5,contacts.getState());
    		  per.setInt(6,contacts.getZip());
    		  per.setString(7, contacts.getPhoneNo());
    		  per.setString(8,contacts.getEmail());
    		  per.setString(9, name);
    		  int set = per.executeUpdate();
    	  }catch(Exception e) {
    		  System.out.println(e.getMessage());
    	  }
      }
      public static  List<Contacts> getByDate(String addressBookName,LocalDate date) throws SQLException {
    	  if(!isPresent(addressBookName)) return null;
    	  List<Contacts> list = new ArrayList<>();
    	  con = ConnectDatabase.getConnection();
    	  String url = "Select * from "+addressBookName+" where date = ?";
    	  PreparedStatement st = con.prepareStatement(url);
    	  st.setString(1, addressBookName);
    	  st.setDate(2, Date.valueOf(date));
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
    		  temp.setDate(LocalDate.parse(set.getDate("date").toString()));
    		  list.add(temp);
    	  }
    	  return list;
      }
      public static Map<String,Integer> countContactsByState(String addressBookName) {
    	  if(!isPresent(addressBookName)) return null;
    	  Map<String,Integer> map = new HashMap<>();

    	    try {
    	        Connection con = ConnectDatabase.getConnection();
    	        String query = "SELECT state, COUNT(*) AS total_contacts FROM ? GROUP BY state";
    	        PreparedStatement ps = con.prepareStatement(query);
    	        ps.setString(1, addressBookName);
    	        ResultSet rs = ps.executeQuery();
    	        while(rs.next()) {
    	            map.put(rs.getString("state"), rs.getInt("total_contacts"));
    	        }
    	    } catch(Exception e) {
    	        e.printStackTrace();
    	    }
    	    return map;
    	}
      public static Map<String,Integer> countContactsByCity(String addressBookName) {
    	  if(!isPresent(addressBookName)) return null;
    	  Map<String,Integer> map = new HashMap<>();

    	    try {
    	        Connection con = ConnectDatabase.getConnection();
    	        String query = "SELECT city, COUNT(*) AS total_contacts FROM "+addressBookName+" GROUP BY city";
    	        PreparedStatement ps = con.prepareStatement(query);
    	        ps.setString(1, addressBookName);
    	        ResultSet rs = ps.executeQuery();
    	        while(rs.next()) {
    	            map.put(rs.getString("city"), rs.getInt("total_contacts"));
    	        }
    	    } catch(Exception e) {
    	        e.printStackTrace();
    	    }
    	    return map;
    	}
      public static boolean isPresent(String s) {
    	 
    	  String url = "SHOW TABLES";
    	  try {
    		  con = ConnectDatabase.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(url);
			while(rs.next()) {
				if(s.equalsIgnoreCase(rs.getString(1))) {
					return true;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  return false;
      }
      
      public static void createTable(String s) {
    	  String url = "CREATE TABLE "+s+" (first_name varchar(50) PRIMARY KEY,last_name varchar(50),address varchar(50),city  varchar(50),state  varchar(50),zip integer,phone_no varchar(50),email varchar(50),date DATE)";
    	  try {
    		  con = ConnectDatabase.getConnection();
			PreparedStatement per = con.prepareStatement(url);
			int result = per.executeUpdate();
			System.out.println("table create successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
}

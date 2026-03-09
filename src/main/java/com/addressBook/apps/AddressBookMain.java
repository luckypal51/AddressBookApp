package com.addressBook.apps;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.addressBook.apps.model.Contacts;
import com.addressBook.database.SQLOperation;

public class AddressBookMain {
	public static Contacts pars( String s) {
		String[] arr = s.split(",");
    	if(arr.length!=8) {
    		throw new IllegalArgumentException("Invalid Input");
    	}
         Contacts con = new Contacts(arr[0],
    			arr[1],arr[2],arr[3],arr[4],
    			Integer.parseInt(arr[5]), arr[6], arr[7]);
         return con;
	}
	public static void addAddressBook(String s) {
		if(!SQLOperation.isPresent(s)) {
			SQLOperation.createTable(s);
			return;
		}
		System.out.println("Address Book Already exists : ");
	}
	public static void addContacts(String addressBookName,String s) {
		Contacts c = pars( s);
		SQLOperation.add(c, addressBookName);
	}
	public static void updateContacts(String addressBookName,String name, String s) {
		Contacts con = pars(s);
		SQLOperation.UpdateDetailInDatabase(addressBookName, name, con);
	}
	public static List<Contacts> view(String addressBookName) throws Exception{
		return SQLOperation.getAll(addressBookName);
	}
	public static List<Contacts> viewByDate(String addressBookName,String date){
		try {
			return SQLOperation.getByDate(addressBookName,LocalDate.parse(date));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Map<String,Integer> countContactByCity(String addressBookName){
		return SQLOperation.countContactsByCity(addressBookName);
	}
	public static Map<String,Integer> countContactByState(String addressBookName){
		return SQLOperation.countContactsByState(addressBookName);
	}
    public static void main(String[] args ) throws Exception{
      
        
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
        	System.out.println("Enter 1 to Add addressBook : ");
        	System.out.println("Enter 2 to Add Contacts    : ");
        	System.out.println("Enter 3 to view Contacts in database : ");
        	System.out.println("Enter 4 to Update Exiting details : ");
        	System.out.println("Enter 5 to view Contacts by date : ");
        	System.out.println("Enter 6 to Count contact by city :");
        	System.out.println("Enter 7 to Count contact by state :");
        	int a = Integer.parseInt(read.readLine());
        	if(a==1) {
        		System.out.println("Enter Address Book Name : ");
        		String s = read.readLine();
        		addAddressBook(s);
        	}else if(a==2) {
        		System.out.println("Enter AddressBook Name : ");
        		String adBook = read.readLine();
        		System.out.println("Enter Contacts Detail as [first_name,last_name,address,city,state,zip,phone_no,email]");
        		String s = read.readLine();
        	    addContacts(adBook, s);
        	}else if(a==3) {
        		System.out.println("Enter AddressBook Name : ");
        		String addBook = read.readLine();
        	    for(Contacts c: view(addBook)) {
        	    	System.out.println(c.toString());
        	    }
        	}else if(a==4) {
        		System.out.println("Enter AddressBook Name : ");
        		String addressBookName = read.readLine();
        		System.out.println("Enter the first Name : ");
        		String name = read.readLine();
        		System.out.println("Enter Updated Details as [first_name,last_name,address,city,state,zip,phone_no,email]");
        		String detail = read.readLine();
        		updateContacts(addressBookName, name, detail);
        		
        	}else if(a==5) {
        		System.out.println("Enter AddressBook Name : ");
        		String addressBookName = read.readLine();
        		System.out.println("Enter date in (YYYY-MM-DD) : ");
        		String date = read.readLine();
        		 for(Contacts c: viewByDate(addressBookName, date)) {
         	    	System.out.println(c.toString());
         	    }
        		
        	}else if(a==6) {
        		System.out.println("Enter AddressBook Name : ");
        		String addresBookName = read.readLine();
        		
        		
        	}else if(a==7) {
        		System.out.println("Enter AddressBook Name : ");
        		String addresBookName = read.readLine();
        	
        	}else {
        		break;
        	}
        }
    }
    
}

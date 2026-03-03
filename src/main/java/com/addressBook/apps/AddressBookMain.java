package com.addressBook.apps;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.addressBook.apps.model.Contacts;

public class AddressBookMain {
    private static List<Contacts> contacts = new ArrayList<>();    //Helps to add multiple person in the Address book App
    
    public static void add(String s) {
    	String[] arr = s.split(":");
    	if(arr.length!=8) {
    		throw new IllegalArgumentException("Invalid Input");
    	}
         Contacts con = new Contacts(arr[0],
    			arr[1],arr[2],arr[3],arr[4],
    			Integer.parseInt(arr[5]), arr[6], arr[7]);
   
    	for(Contacts c : contacts) {// This helps to find if the person with input name already exists or not 
    		if(c.equals(con)) { // Override the equals method to check name  
    			System.out.println("The Name Already exist : "+c.getFirstName()+" "+c.getLastName());
    			return;
    		}
    	}
    	contacts.add(con);
    }
    public static void update(String name ,String s) {
    	String[] arr = s.split(":");
    	if(arr.length!=8) {
    		throw new IllegalArgumentException("Invalid Input");
    	}
    	for(Contacts c: contacts) {
    		if((name).equalsIgnoreCase(c.getFirstName()+" "+c.getLastName())) {
    			c.setFirstName(arr[0]);
    			c.setLastName(arr[1]);
    			c.setAddress(arr[2]);
    			c.setCity(arr[3]);
    			c.setState(arr[4]);
    			c.setZip(Integer.parseInt(arr[5]));
    			c.setPhoneNo(arr[6]);
    			c.setEmail(arr[7]);
    			return;
    		}
    	}
    	System.out.println("User Not Found ");
    }
    public static void delete(String name) {
    	for(Contacts c: contacts) {
    		if(name.equalsIgnoreCase(c.getFirstName()+" "+c.getLastName())) {
    			System.out.println("Deleted contact : "+c.toString());
    			contacts.remove(c);
    			return;
    		}
    	}
    	System.out.println("User Not Found");
    }
    
    public static List<Contacts> searchByCity(String city){
    	return contacts.stream().filter(s->s.getCity().equalsIgnoreCase(city)).toList();
    }
    
    public static List<Contacts> searchByState(String state){
    	return contacts.stream().filter(s->s.getState().equalsIgnoreCase(state)).toList();
    }
    
    public static void main(String[] args ) throws IOException{
      
    	add("lucky:pal:berkhera:bhopal:MP:12345:83056144536:pallucky936@gmail.com");
    	add("Himesh:kurmi:baisa:sagar:MP:462022:89564122121:himeshkurmi@gmail.com");
    	add("nageshwar:patel:maiyar:katni:MP:11111:7845129654:nageshwar@gmail.com");
    	add("lucky:pal:berkhera:bhopal:MP:12345:83056144536:pallucky936@gmail.com");
    	for(Contacts c : contacts) {
    		System.out.println(c.toString());
    	}
    	update("himesh kurmi","Himesh:kurmi:Anand Nager:Bhopal:MH:462022:89564122121:himeshkurmi@gmail.com ");
    	System.out.println("\n");
    	for(Contacts c : contacts) {
    		System.out.println(c.toString());
    	}
    	System.out.println("\n");
    	delete("nageshwar patel");
    	System.out.println("\n");
    	for(Contacts c : contacts) {
    		System.out.println(c.toString());
    	}
    	
    	System.out.println("\n");
    	
    	System.out.println("Contacts search by city : "+searchByCity("bhopal").toString()); 
    	
    	System.out.println("\n");
    	
    	System.out.println("Contacts search by State : "+searchByState("Mh").toString());
    }
    
}

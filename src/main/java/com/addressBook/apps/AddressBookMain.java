package com.addressBook.apps;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.addressBook.apps.model.Contacts;

public class AddressBookMain {
	static Map<String,AddressBook> addressBook =new HashMap<>();
	
	public static void addAddress(String name) {
		name = name.toLowerCase();
		if(addressBook.containsKey(name)) {
			System.out.println("AddressBook Already exists"); 
			return;
		}
		AddressBook ad = new AddressBook(name);
		addressBook.put(name, ad);
	}
	public static void addContacts(String addressBookName,String con) {
		addressBookName = addressBookName.toLowerCase();
		AddressBook ad = addressBook.get(addressBookName);
		ad.add(con);
	}
	
	public static  void updateContact(String addressBookName,String contactName,String s) {
		addressBookName = addressBookName.toLowerCase();
		AddressBook ad = addressBook.get(addressBookName);
		ad.update(contactName, s);
	}
	
	public static void deleteContact(String addressBookName,String contactName) {
		addressBookName = addressBookName.toLowerCase();
		AddressBook ad = addressBook.get(addressBookName);
		ad.delete(contactName);
	}
	
	public static List<Contacts> searchByCity(String addressBookName,String city){
		addressBookName = addressBookName.toLowerCase();
		AddressBook ad = addressBook.get(addressBookName);
		return ad.searchByCity(city);
	}
	
	public static List<Contacts> searchByState(String addressBookName,String state){
		addressBookName = addressBookName.toLowerCase();
		AddressBook ad = addressBook.get(addressBookName);
		return ad.searchByState(state);
	}
	
	public static Map<String,List<Contacts>> mapByCity(String addressBookName){
		addressBookName = addressBookName.toLowerCase();
		AddressBook ad = addressBook.get(addressBookName);
		return ad.getMapByCity();
	}
	public static Map<String,List<Contacts>> mapByState(String addressBookName){
		addressBookName = addressBookName.toLowerCase();
		AddressBook ad = addressBook.get(addressBookName);
		return ad.getMapByState();
	}
	
	public static List<Contacts> viewContact(String addressBookName){
		addressBookName = addressBookName.toLowerCase();
		AddressBook ad = addressBook.get(addressBookName);
		return ad.getContacts();
	}
	
	public static Map<String,List<Contacts>> getMapByCity(String addressBookName){
		addressBookName = addressBookName.toLowerCase();
		AddressBook ad = addressBook.get(addressBookName);
		return ad.getMapByCity();
	}
	
	public static Map<String,List<Contacts>> getMapByState(String addressBookName){
		addressBookName = addressBookName.toLowerCase();
		AddressBook ad = addressBook.get(addressBookName);
		return ad.getMapByState();
	}
	
	public static List<Contacts>searchByCity(String city){
		city = city.toLowerCase();
		List<Contacts> list = new ArrayList<>();
		for(var a : addressBook.entrySet()) {
			if(a.getValue().getMapByCity().containsKey(city)) {
				list.addAll(a.getValue().getMapByCity().get(city));
			}
		}
		return list;
	}
	
	public static List<Contacts> searchByState(String state){
		state = state.toLowerCase();
		List<Contacts> list = new ArrayList<>();
		for(var a : addressBook.entrySet()) {
			if(a.getValue().getMapByState().containsKey(state)) {
				list.addAll(a.getValue().getMapByState().get(state));
			}
		}
		return list;
	}
	
	public static Map<String,Integer> countByCity(String addressBookName){
		addressBookName = addressBookName.toLowerCase();
		AddressBook ad = addressBook.get(addressBookName);
		
		return ad.countByCity();
	}
	
	public static Map<String,Integer> countByState(String addressBookName){
		addressBookName = addressBookName.toLowerCase();
		AddressBook ad = addressBook.get(addressBookName);
		
		return ad.countByState();
	}
	public static List<Contacts> sortByName(String addressBookName){
		addressBookName = addressBookName.toLowerCase();
		AddressBook ad = addressBook.get(addressBookName);
		return ad.sortByName();
	}
    public static void main(String[] args ) throws IOException{
        addAddress("Book1");
        
        addContacts("Book1","lucky:pal:berkhera:bhopal:MP:12345:83056144536:pallucky936@gmail.com");
        addContacts("Book1","Himesh:kurmi:baisa:sagar:MP:462022:89564122121:himeshkurmi@gmail.com");
        addContacts("Book1", "nageshwar:patel:maiyar:katni:MP:11111:7845129654:nageshwar@gmail.com");
        addContacts("Book1","lucky:pal:berkhera:bhopal:MP:12345:83056144536:pallucky936@gmail.com");
        
        System.out.println("\n");
        System.out.println(viewContact("book1"));
        System.out.println("\n");
        
        updateContact("Book1","Himesh kurmi", "Himesh:kurmi:Anand Nager:Bhopal:MH:462022:89564122121:himeshkurmi@gmail.com");
        System.out.println("\n");
        System.out.println(viewContact("book1"));
        System.out.println("\n");
        
        deleteContact("book1", "lucky pal");
        
        System.out.println("\n");
        System.out.println(viewContact("book1"));
        System.out.println("\n");
        
        addContacts("Book1","lucky:pal:berkhera:bhopal:MP:12345:83056144536:pallucky936@gmail.com");
        
        System.out.println("\nMapByCity : "+getMapByCity("book1"));
        System.out.println("\n");
        System.out.println("MapByState : "+getMapByState("Book1"));
        
        System.out.println("\nCount people in city addressBook 1 : "+countByCity("Book1")); 
        System.out.println("\nCount people in state in addressBook 1 : "+countByState("Book1"));
        
        System.out.println("\nsorted by name : "+sortByName("Book1"));
    }
    
}

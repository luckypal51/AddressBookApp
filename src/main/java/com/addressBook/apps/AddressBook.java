package com.addressBook.apps;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.addressBook.apps.model.Contacts;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class AddressBook {
	   private String addressBookName;
	   private  List<Contacts> contacts = new ArrayList<>();
	   private Map<String,Contacts> mapByName = new HashMap<>();
	   private  Map<String,List<Contacts>> mapByCity = new HashMap<>();
	   private Map<String,List<Contacts>> mapByState = new HashMap<>();
	    
	    public AddressBook(String addressBookName) {
	    	this.addressBookName = addressBookName;
	    }
	     

		public String getAddressBookName() {
			return addressBookName;
		}


		public void setAddressBookName(String addressBookName) {
			this.addressBookName = addressBookName;
		}

        
		public List<Contacts> getContacts() {
			return contacts;
		}


		public Map<String, Contacts> getMapByName() {
			return mapByName;
		}


		public Map<String, List<Contacts>> getMapByCity() {
			return mapByCity;
		}


		public Map<String, List<Contacts>> getMapByState() {
			return mapByState;
		}


		public  void add(String s) {
	    	String[] arr = s.split(",");
	    	if(arr.length!=8) {
	    		throw new IllegalArgumentException("Invalid Input");
	    	}
	         Contacts con = new Contacts(arr[0],
	    			arr[1],arr[2],arr[3],arr[4],
	    			Integer.parseInt(arr[5]), arr[6], arr[7]);
	         String key = (con.getFirstName()+" "+con.getLastName()).toLowerCase();
	         if(mapByName.containsKey(key)) {
	        	 System.out.println("Name already exits : "+con.getFirstName()+" "+con.getLastName());
	        	 return;
	         }
	         
	         
	    	contacts.add(con);
	    	
	    	
	    	mapByName.put(key,con);
	    	
	    	key = con.getCity().toLowerCase();
	    	if(mapByCity.containsKey(key)) {
	    		List<Contacts> city = mapByCity.get(key);
	    		city.add(con);
	    		mapByCity.put(key,city);
	    	}else {
	    		List<Contacts> city = new ArrayList<>();
	    		city.add(con);
	    		mapByCity.put(key,city);
	    	}
	    	key = con.getState().toLowerCase();
	    	if(mapByState.containsKey(key)) {
	    		List<Contacts> state = mapByState.get(key);
	    		state.add(con);
	    		mapByState.put(key,state);
	    	}else {
	    		List<Contacts> state = new ArrayList<>();
	    		state.add(con);
	    		mapByState.put(key,state);
	    	}
	    }
		
		public  void add(Contacts con) {
	         String key = (con.getFirstName()+" "+con.getLastName()).toLowerCase();
	         if(mapByName.containsKey(key)) {
	        	 System.out.println("Name already exits : "+con.getFirstName()+" "+con.getLastName());
	        	 return;
	         }
	         
	         
	    	contacts.add(con);
	    	
	    	
	    	mapByName.put(key,con);
	    	
	    	key = con.getCity().toLowerCase();
	    	if(mapByCity.containsKey(key)) {
	    		List<Contacts> city = mapByCity.get(key);
	    		city.add(con);
	    		mapByCity.put(key,city);
	    	}else {
	    		List<Contacts> city = new ArrayList<>();
	    		city.add(con);
	    		mapByCity.put(key,city);
	    	}
	    	key = con.getState().toLowerCase();
	    	if(mapByState.containsKey(key)) {
	    		List<Contacts> state = mapByState.get(key);
	    		state.add(con);
	    		mapByState.put(key,state);
	    	}else {
	    		List<Contacts> state = new ArrayList<>();
	    		state.add(con);
	    		mapByState.put(key,state);
	    	}
	    }
		
		public  void add(String[] arr) {
	    	if(arr.length!=8) {
	    		throw new IllegalArgumentException("Invalid Input");
	    	}
	         Contacts con = new Contacts(arr[0],
	    			arr[1],arr[2],arr[3],arr[4],
	    			Integer.parseInt(arr[5]), arr[6], arr[7]);
	         String key = (con.getFirstName()+" "+con.getLastName()).toLowerCase();
	         if(mapByName.containsKey(key)) {
	        	 System.out.println("Name already exits : "+con.getFirstName()+" "+con.getLastName());
	        	 return;
	         }
	         
	         
	    	contacts.add(con);
	    	
	    	
	    	mapByName.put(key,con);
	    	
	    	key = con.getCity().toLowerCase();
	    	if(mapByCity.containsKey(key)) {
	    		List<Contacts> city = mapByCity.get(key);
	    		city.add(con);
	    		mapByCity.put(key,city);
	    	}else {
	    		List<Contacts> city = new ArrayList<>();
	    		city.add(con);
	    		mapByCity.put(key,city);
	    	}
	    	key = con.getState().toLowerCase();
	    	if(mapByState.containsKey(key)) {
	    		List<Contacts> state = mapByState.get(key);
	    		state.add(con);
	    		mapByState.put(key,state);
	    	}else {
	    		List<Contacts> state = new ArrayList<>();
	    		state.add(con);
	    		mapByState.put(key,state);
	    	}
	    }
	    public  void update(String name ,String s) {
	    	String[] arr = s.split(":");
	    	if(arr.length!=8) {
	    		throw new IllegalArgumentException("Invalid Input");
	    	}
	    	
	    	Contacts temp = new Contacts(arr[0], arr[1],arr[2],arr[3],arr[4],Integer.parseInt(arr[5]),arr[6],arr[7]);
	    	
	    	for(Contacts c: contacts) {
	    		if((name).equalsIgnoreCase(c.getFirstName()+" "+c.getLastName())) {
	    			
	    			mapByName.remove(c.getFirstName()+" "+c.getLastName());
	    			mapByName.put(temp.getFirstName()+" "+temp.getLastName(),temp);
	    			 String key =c.getCity().toLowerCase();
	    			 List<Contacts> city1 = mapByCity.get(key);
	                 city1.remove(c);
	                 
	                 mapByCity.put(key,city1);
	                 
	                 key = c.getState().toLowerCase();
	                 List<Contacts> state1 = mapByState.get(key);
	                 state1.remove(c);
	                 mapByState.put(key, state1);
	                 
	                 key = temp.getCity().toLowerCase();
	                 if(mapByCity.containsKey(key)) {
	             		List<Contacts> city = mapByCity.get(key);
	             		city.add(temp);
	             		mapByCity.put(key,city);
	             	}else {
	             		List<Contacts> city = new ArrayList<>();
	             		city.add(temp);
	             		mapByCity.put(key,city);
	             	}
	             	key = temp.getState().toLowerCase();
	             	if(mapByState.containsKey(key)) {
	             		List<Contacts> state = mapByState.get(key);
	             		state.add(temp);
	             		mapByState.put(key,state);
	             	}else {
	             		List<Contacts> state = new ArrayList<>();
	             		state.add(temp);
	             		mapByState.put(key,state);
	             	}
	    			 c.setAddress(temp.getAddress());
	    			 c.setCity(temp.getCity());
	    			 c.setEmail(temp.getEmail());
	    			 c.setFirstName(temp.getFirstName());
	    			 c.setLastName(temp.getLastName());
	    			 c.setPhoneNo(temp.getPhoneNo());
	    			 c.setState(temp.getState());
	    			 c.setZip(temp.getZip());
	    			 return;
	    		}
	    	}
	    	System.out.println("User Not Found ");
	    }
	    public  void update(String name ,String[]arr) {
	    	if(arr.length!=8) {
	    		throw new IllegalArgumentException("Invalid Input");
	    	}
	    	
	    	Contacts temp = new Contacts(arr[0], arr[1],arr[2],arr[3],arr[4],Integer.parseInt(arr[5]),arr[6],arr[7]);
	    	
	    	for(Contacts c: contacts) {
	    		if((name).equalsIgnoreCase(c.getFirstName()+" "+c.getLastName())) {
	    			
	    			mapByName.remove(c.getFirstName()+" "+c.getLastName());
	    			mapByName.put(temp.getFirstName()+" "+temp.getLastName(),temp);
	    			 String key =c.getCity().toLowerCase();
	    			 List<Contacts> city1 = mapByCity.get(key);
	                 city1.remove(c);
	                 
	                 mapByCity.put(key,city1);
	                 
	                 key = c.getState().toLowerCase();
	                 List<Contacts> state1 = mapByState.get(key);
	                 state1.remove(c);
	                 mapByState.put(key, state1);
	                 
	                 key = temp.getCity().toLowerCase();
	                 if(mapByCity.containsKey(key)) {
	             		List<Contacts> city = mapByCity.get(key);
	             		city.add(temp);
	             		mapByCity.put(key,city);
	             	}else {
	             		List<Contacts> city = new ArrayList<>();
	             		city.add(temp);
	             		mapByCity.put(key,city);
	             	}
	             	key = temp.getState().toLowerCase();
	             	if(mapByState.containsKey(key)) {
	             		List<Contacts> state = mapByState.get(key);
	             		state.add(temp);
	             		mapByState.put(key,state);
	             	}else {
	             		List<Contacts> state = new ArrayList<>();
	             		state.add(temp);
	             		mapByState.put(key,state);
	             	}
	    			 c.setAddress(temp.getAddress());
	    			 c.setCity(temp.getCity());
	    			 c.setEmail(temp.getEmail());
	    			 c.setFirstName(temp.getFirstName());
	    			 c.setLastName(temp.getLastName());
	    			 c.setPhoneNo(temp.getPhoneNo());
	    			 c.setState(temp.getState());
	    			 c.setZip(temp.getZip());
	    			 return;
	    		}
	    	}
	    	System.out.println("User Not Found ");
	    }
	    public void delete(String name) {
	    	for(Contacts con: contacts) {
		         String key = (con.getFirstName()+" "+con.getLastName()).toLowerCase();

	    		if(name.equalsIgnoreCase(key)) {
	    			System.out.println("Deleted contact : "+con.toString());
	    			
	              mapByName.remove(key);
	              
	              key = con.getCity().toLowerCase();
	              List<Contacts> city = mapByCity.get(key);
	              city.remove(con);
	              mapByCity.put(key,city);
	              
	              key = con.getState().toLowerCase();
	              List<Contacts> state = mapByState.get(key);
	              state.remove(con);
	              mapByState.put(key, state);
	              
	              contacts.remove(con);
	              return;
	    		}
	    	}
	    	System.out.println("User Not Found");
	    }
	    
	    public  List<Contacts> searchByCity(String city){
	    	city = city.toLowerCase();
	    	return mapByCity.get(city);
	    }
	    
	    public  List<Contacts> searchByState(String state){
	    	state = state.toLowerCase();
	    	return mapByState.get(state);
	    }
	    
	   public Map<String,Integer> countByCity(){
		   return mapByCity.entrySet().stream().collect(Collectors.toMap(a->a.getKey(),a->a.getValue().size()));
	   }
	   
	   public Map<String,Integer> countByState(){
		   return mapByState.entrySet().stream().collect(Collectors.toMap(a->a.getKey(),a->a.getValue().size()));
	   }
	   
	   public List<Contacts> sortByName() {
		   return contacts.stream().sorted(Comparator.comparing(Contacts::getFirstName).thenComparing(Comparator.comparing(Contacts::getLastName))).toList();
	   }
	   
	   public List<Contacts> sortByCity(){
		   return contacts.stream().sorted(Comparator.comparing(Contacts::getCity)).toList();
	   }
	   
	   public List<Contacts> sortByState(){
		   return contacts.stream().sorted(Comparator.comparing(Contacts::getState)).toList();
	   }
	   
	   public List<Contacts> sortByZipCode(){
		   return contacts.stream().sorted(Comparator.comparing(Contacts::getZip)).toList();
	   }
	   
	   public void writeInfile(String s) throws IOException{
		   FileOutputStream file = new FileOutputStream(s);
		  for(Contacts c: contacts) {
			  file.write(c.toString().getBytes());
		  }
	   }
	   
	   public void readCSVFile(String path) throws Exception{
		   CSVReader read = new CSVReader(new FileReader(path));
		   String[] line;
		   while((line=read.readNext())!=null) {
			    add(line);
		   }
	   }
	   public void writeInCsvFile(String path) throws Exception{
		   CSVWriter write = new CSVWriter(new FileWriter(path));
		   for(Contacts c: contacts) {
			   String[] arr = new String[8];
			   arr[0] = c.getFirstName();
			   arr[1] = c.getLastName();
			   arr[2] = c.getAddress();
			   arr[3] = c.getCity();
			   arr[4] = c.getState();
			   arr[5] = Integer.toString(c.getZip());
			   arr[6] = c.getPhoneNo();
			   arr[7] = c.getEmail();
			   write.writeNext(arr);
		   }
	   write.close();
	   }
	 
}

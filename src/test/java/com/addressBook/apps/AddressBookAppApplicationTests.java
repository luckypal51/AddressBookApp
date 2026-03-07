package com.addressBook.apps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.addressBook.database.SQLOperation;

@SpringBootTest
class AddressBookAppApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void getDetailTest() {
		try {
			assertEquals(1,SQLOperation.getAll("book2").size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

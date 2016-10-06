package com.sutta.collab.JUTest;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sutta.collab.dao.UserDetailsDAO;
import com.sutta.collab.model.UserDetails;


import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class UserDetailsJUTest {
	
	Logger log = LoggerFactory.getLogger(UserDetailsJUTest.class);

	private static AnnotationConfigApplicationContext context;

	@Autowired
	private static UserDetails userDetails;

	@Autowired
	private static UserDetailsDAO userDetailsDAO;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sutta.collab");
		context.refresh();
		
		userDetails = (UserDetails) context.getBean("userDetails");
		
		userDetailsDAO = (UserDetailsDAO) context.getBean("userDetailsDAO");
	}
	
	@AfterClass
	public static void close(){
		context.close();
		userDetails = null;
		userDetailsDAO = null;
	}

	@Test
	public void userDetailsCrudTestCase() {

		/*userDetailsCreateTestCase*/
		userDetails.setId("TEST01");
		userDetails.setName("Test Name");
		userDetails.setEmail("Test Email");
		userDetails.setPassword("Test Password");
		userDetails.setMobile("9999999999");
		userDetails.setAddress("Test Address");
		userDetails.setRole("admin");
		
		Assert.assertEquals("userDetailsCreateTestCase", true, userDetailsDAO.save(userDetails));
		
		/*getUserDetailsTestCase*/
		Assert.assertEquals("getUserDetailsTestCase",true,userDetailsDAO.get("TEST01")!=null);
		
		/*userDetailsUpdateTestCase*/
		userDetails.setId("TEST01");
		userDetails.setName("Test Name Updated");
		userDetails.setEmail("Test Email Updated");
		userDetails.setPassword("Test Password Updated" );
		userDetails.setMobile("9999999999");
		userDetails.setAddress("Test Address Updated");
		userDetails.setRole("admin");
		
		Assert.assertEquals("userDetailsUpdateTestCase", true, userDetailsDAO.update(userDetails));
		
		/*userDetailsDeleteTestCase*/
		Assert.assertEquals("userDetailsDeleteTestCase", true, userDetailsDAO.delete("TEST01"));


	}


}

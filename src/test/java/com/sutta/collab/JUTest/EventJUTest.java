package com.sutta.collab.JUTest;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sutta.collab.dao.EventDAO;
import com.sutta.collab.model.Event;


import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class EventJUTest {
	
	Logger log = LoggerFactory.getLogger(EventJUTest.class);

	private static AnnotationConfigApplicationContext context;

	@Autowired
	private static Event event;

	@Autowired
	private static EventDAO eventDAO;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sutta.collab");
		context.refresh();
		
		event = (Event) context.getBean("event");
		
		eventDAO = (EventDAO) context.getBean("eventDAO");
	}
	
	@AfterClass
	public static void close(){
		context.close();
		event = null;
		eventDAO = null;
	}

	@Test
	public void eventCrudTestCase() {

		/*eventCreateTestCase*/
		event.setId("TEST01");
		event.setContent("Test Content");
		event.setDescription("TEST Description");
		Assert.assertEquals("eventCreateTestCase", true, eventDAO.save(event));
		
		/*getEventTestCase*/
		Assert.assertEquals("getEventTestCase",true,eventDAO.get("TEST01")!=null);
		
		/*eventUpdateTestCase*/
		event.setId("E01");
		event.setContent("Test Content Updated");
		event.setDescription("TEST Description Updated");
		
		Assert.assertEquals("eventUpdateTestCase", true, eventDAO.update(event));
		
		/*eventDeleteTestCase*/
		Assert.assertEquals("eventDeleteTestCase", true, eventDAO.delete("TEST01"));


	}


}

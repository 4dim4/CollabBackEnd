package com.sutta.collab.JUTest;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sutta.collab.dao.JobDAO;
import com.sutta.collab.model.Job;


import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class JobJUTest {
	
	Logger log = LoggerFactory.getLogger(JobJUTest.class);

	private static AnnotationConfigApplicationContext context;

	@Autowired
	private static Job job;

	@Autowired
	private static JobDAO jobDAO;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sutta.collab");
		context.refresh();
		
		job = (Job) context.getBean("job");
		
		jobDAO = (JobDAO) context.getBean("jobDAO");
	}
	
	@AfterClass
	public static void close(){
		context.close();
		job = null;
		jobDAO = null;
	}

	@Test
	public void jobCrudTestCase() {

		/*jobCreateTestCase*/
		job.setId("TEST01");
		job.setTitle("Test title");
		job.setContent("Test Content");
		
		Assert.assertEquals("jobCreateTestCase", true, jobDAO.save(job));
		
		/*getJobTestCase*/
		Assert.assertEquals("getJobTestCase",true,jobDAO.get("TEST01")!=null);
		
		/*jobUpdateTestCase*/
		job.setId("TEST01");
		job.setTitle("Test title Updated");
		job.setContent("Test Content Updated");
		
		Assert.assertEquals("jobUpdateTestCase", true, jobDAO.update(job));
		
		/*jobDeleteTestCase*/
		Assert.assertEquals("jobDeleteTestCase", true, jobDAO.delete("TEST01"));


	}


}

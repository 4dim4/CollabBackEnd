package com.sutta.collab.JUTest;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sutta.collab.dao.ForumDAO;
import com.sutta.collab.model.Forum;


import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ForumJUTest {
	
	Logger log = LoggerFactory.getLogger(ForumJUTest.class);

	private static AnnotationConfigApplicationContext context;

	@Autowired
	private static Forum forum;

	@Autowired
	private static ForumDAO forumDAO;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sutta.collab");
		context.refresh();
		
		forum = (Forum) context.getBean("forum");
		
		forumDAO = (ForumDAO) context.getBean("forumDAO");
	}
	
	@AfterClass
	public static void close(){
		context.close();
		forum = null;
		forumDAO = null;
	}

	@Test
	public void forumCrudTestCase() {

		/*forumCreateTestCase*/
		forum.setId("TEST01");
		forum.setTitle("New Forum Test");
		forum.setUserId("User Test");
		forum.setStatus('A');
		forum.setDescription("new test");
		Assert.assertEquals("forumCreateTestCase", true, forumDAO.save(forum));
		
		/*getForumTestCase*/
		Assert.assertEquals("getForumTestCase",true,forumDAO.get("TEST01")!=null);
		
		/*forumUpdateTestCase*/
		forum.setId("TEST01");
		forum.setTitle("New Forum Test Updated");
		forum.setUserId("User Test Updated");
		forum.setStatus('A');
		forum.setDescription("new test Updated");
		
		Assert.assertEquals("forumUpdateTestCase", true, forumDAO.update(forum));
		
		/*forumDeleteTestCase*/
		Assert.assertEquals("forumDeleteTestCase", true, forumDAO.delete("TEST01"));


	}


}

package com.sutta.collab.JUTest;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sutta.collab.dao.ForumPostDAO;
import com.sutta.collab.model.ForumPost;


import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ForumPostJUTest {
	
	Logger log = LoggerFactory.getLogger(ForumPostJUTest.class);

	private static AnnotationConfigApplicationContext context;

	@Autowired
	private static ForumPost forumPost;

	@Autowired
	private static ForumPostDAO forumPostDAO;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sutta.collab");
		context.refresh();
		
		forumPost = (ForumPost) context.getBean("forumPost");
		
		forumPostDAO = (ForumPostDAO) context.getBean("forumPostDAO");
	}
	
	@AfterClass
	public static void close(){
		context.close();
		forumPost = null;
		forumPostDAO = null;
	}

	@Test
	public void forumPostCrudTestCase() {

		/*forumPostCreateTestCase*/
	
		forumPost.setContent("Test Content");
		forumPost.setThreadId("TestThread01");
		forumPost.setUserId("TestUser01");
	    log.info("\n>>>"+"forum_post_id : {}",forumPost.getId()+"<<<\n");
		Assert.assertEquals("forumPostCreateTestCase", true, forumPostDAO.save(forumPost));
		log.info("\n>>>forum_post_id : {}",forumPost.getId()+"<<<\n");
		/*getForumPostTestCase*/
		Assert.assertEquals("getForumPostTestCase",true,forumPostDAO.get(forumPost.getId())!=null);
		
		/*forumPostUpdateTestCase*/
	
		forumPost.setContent("Test Content Updated");
		
		Assert.assertEquals("forumPostUpdateTestCase", true, forumPostDAO.update(forumPost));
		
		/*forumPostDeleteTestCase*/
		Assert.assertEquals("forumPostDeleteTestCase", true, forumPostDAO.delete(forumPost.getId()));


	}
	
	



}

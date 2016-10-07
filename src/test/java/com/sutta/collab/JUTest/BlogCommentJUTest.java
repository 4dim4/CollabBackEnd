package com.sutta.collab.JUTest;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sutta.collab.dao.BlogCommentDAO;
import com.sutta.collab.model.BlogComment;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class BlogCommentJUTest {
	
	Logger log = LoggerFactory.getLogger(BlogCommentJUTest.class);

	private static AnnotationConfigApplicationContext context;

	@Autowired
	private static BlogComment blogComment;

	@Autowired
	private static BlogCommentDAO blogCommentDAO;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sutta.collab");
		context.refresh();
		
		blogComment = (BlogComment) context.getBean("blogComment");
		
		blogCommentDAO = (BlogCommentDAO) context.getBean("blogCommentDAO");
	}
	
	@AfterClass
	public static void close(){
		context.close();
		blogComment = null;
		blogCommentDAO = null;
	}

	@Test
	
	public void blogCommentCrudTestCase() {

		/*blogCommentCreateTestCase*/
	
		blogComment.setContent("Test Content");
		blogComment.setDescription("TEST Description");
		blogComment.setBlogId("TestBlog01");
		blogComment.setUserId("TestUser01");
		blogComment.setStatus('A');
		Assert.assertEquals("blogCommentCreateTestCase", true, blogCommentDAO.save(blogComment));
		
		/*getBlogCommentTestCase*/
		Assert.assertEquals("getBlogCommentTestCase",true,blogCommentDAO.get(blogComment.getId())!=null);
		
		/*blogCommentUpdateTestCase*/
	
		blogComment.setContent("Test Content Updated");
		blogComment.setDescription("TEST Description Updated");
		blogComment.setBlogId("TestBlog01");
		blogComment.setUserId("TestUser01");
		blogComment.setStatus('A');
		Assert.assertEquals("blogCommentUpdateTestCase", true, blogCommentDAO.update(blogComment));
		
		/*blogCommentDeleteTestCase*/
		Assert.assertEquals("blogCommentDeleteTestCase", true, blogCommentDAO.delete(blogComment.getId()));


	}
	
	@Test
	public void listBlogCommentsByBlogId() {
		
		blogComment.setContent("Test Content");
		blogComment.setDescription("TEST Description");
		blogComment.setBlogId("TestBlog01");
		blogComment.setUserId("TestUser01");
		blogComment.setStatus('A');
		
		blogCommentDAO.save(blogComment);
		blogCommentDAO.save(blogComment);
		
		Assert.assertEquals("listBlogCommentsByBlogId", 2, blogCommentDAO.get("TestBlog01").size());
		
		Assert.assertEquals("deleteBlogCommentsByBlogId", true,blogCommentDAO.delete("TestBlog01"));
		
		
	}
	
	



}

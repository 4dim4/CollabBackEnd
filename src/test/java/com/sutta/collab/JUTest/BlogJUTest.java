package com.sutta.collab.JUTest;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sutta.collab.dao.BlogDAO;
import com.sutta.collab.model.Blog;


import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class BlogJUTest {
	
	Logger log = LoggerFactory.getLogger(BlogJUTest.class);

	private static AnnotationConfigApplicationContext context;

	@Autowired
	private static Blog blog;

	@Autowired
	private static BlogDAO blogDAO;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sutta.collab");
		context.refresh();
		
		blog = (Blog) context.getBean("blog");
		
		blogDAO = (BlogDAO) context.getBean("blogDAO");
	}
	
	@AfterClass
	public static void close(){
		context.close();
		blog = null;
		blogDAO = null;
	}

	@Test
	public void blogCrudTestCase() {

		/*blogCreateTestCase*/
		blog.setId("B06");
		blog.setTitle("New Blog");
		blog.setUserId("sutta");
		blog.setDescription("Blog blog blog blog");
		blog.setStatus('A');
		
		Assert.assertEquals("blogCreateTestCase", true, blogDAO.save(blog));
		
		/*getBlogTestCase*/
		Assert.assertEquals("getBlogTestCase",true,blogDAO.get("B06")!=null);
		
		/*blogUpdateTestCase*/
		blog.setId("B05");
		blog.setTitle("New Blog CHANGED");
		blog.setUserId("sutta");
		blog.setDescription("Blog blog blog blog BLOG");
		blog.setStatus('A');
		
		Assert.assertEquals("blogUpdateTestCase", true, blogDAO.update(blog));
		
		/*blogDeleteTestCase*/
		Assert.assertEquals("blogDeleteTestCase", true, blogDAO.delete("B06"));


	}

/*	@Test
	public void listAllBlogTestCase() {
		log.info("\n*************\n bloglist : {}\n*************\n",blogDAO.list().size());
		Assert.assertEquals("listAllBlogTestCase", 5, blogDAO.list().size());
	}
	
	@Test
	public void getBlogTestCase() {
		
		Assert.assertEquals("getBlogTestCase",true,blogDAO.get("B01")!=null);
	}

	@Test
	public void blogUpdateTestCase() {

		blog.setId("B05");
		blog.setTitle("New Blog CHANGED");
		blog.setUserId("sutta");
		blog.setDescription("Blog blog blog blog BLOG");
		blog.setStatus('A');
		
		Assert.assertEquals("blogUpdateTestCase", true, blogDAO.update(blog));

	
	}

	@Test
	public void blogDeleteTestCase() {

		Assert.assertEquals("blogDeleteTestCase", true, blogDAO.delete("hridesh"));

	}*/
}

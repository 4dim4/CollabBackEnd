package com.sutta.collab.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sutta.collab.dao.BlogDAO;
import com.sutta.collab.model.Blog;


public class BlogTest {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		context.scan("com.sutta.collab");
		context.refresh();
		
		Blog blog = (Blog) context.getBean("blog");
		BlogDAO blogDAO = (BlogDAO) context.getBean("blogDAO");
		
		blog.setId("B0009");
		
		blog.setDescription("TEST");
		
		/*blogDAO.save(blog);*/
		blogDAO.update(blog);
		
		
		context.close();
	}
	
}

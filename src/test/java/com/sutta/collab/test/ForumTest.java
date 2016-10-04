package com.sutta.collab.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sutta.collab.dao.ForumDAO;
import com.sutta.collab.model.Forum;


public class ForumTest {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		context.scan("com.sutta.collab");
		context.refresh();
		
		Forum forum = (Forum) context.getBean("forum");
		ForumDAO forumDAO = (ForumDAO) context.getBean("forumDAO");
		
		forum.setId("B023");
		forum.setTitle("New Forum");
		forum.setUserId("User");
		forum.setStatus('A');
		forum.setDescription("new test");
		
		forumDAO.update(forum);
		
		context.close();
	}
	
}

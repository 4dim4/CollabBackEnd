package com.sutta.collab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sutta.collab.dao.ForumPostDAO;
import com.sutta.collab.model.ForumPost;

@RestController
public class ForumPostController {
	@Autowired
	List<ForumPost> listForumPost;
	
	@Autowired
	ForumPost forumPost;

	@Autowired
	ForumPostDAO forumPostDAO;

	@GetMapping("/ForumPost/")
	public ResponseEntity<List<ForumPost>> listAllForumPost() {
		List<ForumPost> listForumPost = forumPostDAO.list();
		if (listForumPost.isEmpty()) {

			return new ResponseEntity<List<ForumPost>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<ForumPost>>(listForumPost, HttpStatus.OK);
	}

	@GetMapping("/ForumPost/{id}")
	public ResponseEntity<List<ForumPost>> getForumPost(@PathVariable("id") String blogId) {
		listForumPost = forumPostDAO.get(blogId);
		if (listForumPost == null) {
			return new ResponseEntity<List<ForumPost>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<ForumPost>>(listForumPost, HttpStatus.OK);

	}

	@PostMapping("/ForumPost/")
	public ResponseEntity<Void> createForumPost(@RequestBody ForumPost forumPost,
			UriComponentsBuilder ucBuilder) {
		if (forumPostDAO.get(forumPost.getId()) != null) {

			return new ResponseEntity<Void>(HttpStatus.CONFLICT);

		}

		/*
		 * role.setId("ROLE_USER"); role.setName("ROLE_USER");
		 */
		forumPostDAO.save(forumPost);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("ForumPost/{id}/").buildAndExpand(forumPost.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}

/*	@PutMapping("/ForumPost/{id}")
	public ResponseEntity<ForumPost> updateForumPost(@PathVariable("id") String id,
			@RequestBody ForumPost forumPost) {
		
		if (forumPostDAO.get(id) == null) {
			return new ResponseEntity<ForumPost>(HttpStatus.NOT_FOUND);
		}
		forumPost.setId(id);

		
		forumPostDAO.update(forumPost);

		return new ResponseEntity<ForumPost>(forumPost, HttpStatus.OK);

	}
*/
	/*@DeleteMapping("/ForumPost/{id}")
	public ResponseEntity<ForumPost> deleteForumPostByBlogId(@PathVariable("id") String blogId) {

		listForumPost = forumPostDAO.get(blogId);
		if (listForumPost == null) {

			return new ResponseEntity<ForumPost>(HttpStatus.NOT_FOUND);
		}

		forumPostDAO.delete(blogId);
		return new ResponseEntity<ForumPost>(HttpStatus.NO_CONTENT);
	}*/

	
	/* @DeleteMapping("/ForumPost/") public ResponseEntity<ForumPost>
	  deleteAllUsers(){
	 
	  forumPostDAO.deleteAllUsers(); return new
	  ResponseEntity<ForumPost>(HttpStatus.NO_CONTENT); }*/
	 

}

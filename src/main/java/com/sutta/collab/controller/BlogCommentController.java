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

import com.sutta.collab.dao.BlogCommentDAO;
import com.sutta.collab.model.BlogComment;

@RestController
public class BlogCommentController {
	@Autowired
	List<BlogComment> listBlogComment;
	
	@Autowired
	BlogComment blogComment;

	@Autowired
	BlogCommentDAO blogCommentDAO;

	@GetMapping("/BlogComment/")
	public ResponseEntity<List<BlogComment>> listAllBlogComment() {
		List<BlogComment> listBlogComment = blogCommentDAO.list();
		if (listBlogComment.isEmpty()) {

			return new ResponseEntity<List<BlogComment>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<BlogComment>>(listBlogComment, HttpStatus.OK);
	}

	@GetMapping("/BlogComment/{id}")
	public ResponseEntity<List<BlogComment>> getBlogComment(@PathVariable("id") String blogId) {
		listBlogComment = blogCommentDAO.get(blogId);
		if (listBlogComment == null) {
			return new ResponseEntity<List<BlogComment>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<BlogComment>>(listBlogComment, HttpStatus.OK);

	}

	@PostMapping("/BlogComment/")
	public ResponseEntity<Void> createBlogComment(@RequestBody BlogComment blogComment,
			UriComponentsBuilder ucBuilder) {
		if (blogCommentDAO.get(blogComment.getId()) != null) {

			return new ResponseEntity<Void>(HttpStatus.CONFLICT);

		}

		/*
		 * role.setId("ROLE_USER"); role.setName("ROLE_USER");
		 */
		blogCommentDAO.save(blogComment);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("BlogComment/{id}/").buildAndExpand(blogComment.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}

/*	@PutMapping("/BlogComment/{id}")
	public ResponseEntity<BlogComment> updateBlogComment(@PathVariable("id") String id,
			@RequestBody BlogComment blogComment) {
		
		if (blogCommentDAO.get(id) == null) {
			return new ResponseEntity<BlogComment>(HttpStatus.NOT_FOUND);
		}
		blogComment.setId(id);

		
		blogCommentDAO.update(blogComment);

		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);

	}
*/
	@DeleteMapping("/BlogComment/{id}")
	public ResponseEntity<BlogComment> deleteBlogCommentByBlogId(@PathVariable("id") String blogId) {

		listBlogComment = blogCommentDAO.get(blogId);
		if (listBlogComment == null) {

			return new ResponseEntity<BlogComment>(HttpStatus.NOT_FOUND);
		}

		blogCommentDAO.delete(blogId);
		return new ResponseEntity<BlogComment>(HttpStatus.NO_CONTENT);
	}

	
	/* @DeleteMapping("/BlogComment/") public ResponseEntity<BlogComment>
	  deleteAllUsers(){
	 
	  blogCommentDAO.deleteAllUsers(); return new
	  ResponseEntity<BlogComment>(HttpStatus.NO_CONTENT); }*/
	 

}

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

import com.sutta.collab.dao.ForumDAO;
import com.sutta.collab.model.Forum;

@RestController
public class ForumController {
	@Autowired
	Forum forum;

	@Autowired
	ForumDAO forumDAO;

	@GetMapping("/Forum/")
	public ResponseEntity<List<Forum>> listAllForum() {
		List<Forum> listForum = forumDAO.list();
		if (listForum.isEmpty()) {

			return new ResponseEntity<List<Forum>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Forum>>(listForum, HttpStatus.OK);
	}

	@GetMapping("/Forum/{id}")
	public ResponseEntity<Forum> getForum(@PathVariable("id") String id) {
		forum = forumDAO.get(id);
		if (forum == null) {
			return new ResponseEntity<Forum>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Forum>(forum, HttpStatus.OK);

	}

	@PostMapping("/Forum/")
	public ResponseEntity<Void> createForum(@RequestBody Forum forum,
			UriComponentsBuilder ucBuilder) {
		if (forumDAO.get(forum.getId()) != null) {

			return new ResponseEntity<Void>(HttpStatus.CONFLICT);

		}

		/*
		 * role.setId("ROLE_USER"); role.setName("ROLE_USER");
		 */
		forumDAO.save(forum);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("Forum/{id}/").buildAndExpand(forum.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}

	@PutMapping("/Forum/{id}")
	public ResponseEntity<Forum> updateForum(@PathVariable("id") String id,
			@RequestBody Forum forum) {
		
		if (forumDAO.get(id) == null) {
			return new ResponseEntity<Forum>(HttpStatus.NOT_FOUND);
		}
		forum.setId(id);

		
		forumDAO.update(forum);

		return new ResponseEntity<Forum>(forum, HttpStatus.OK);

	}

	@DeleteMapping("/Forum/{id}")
	public ResponseEntity<Forum> deleteForum(@PathVariable("id") String id) {

		forum = forumDAO.get(id);
		if (forum == null) {

			return new ResponseEntity<Forum>(HttpStatus.NOT_FOUND);
		}

		forumDAO.delete(id);
		return new ResponseEntity<Forum>(HttpStatus.NO_CONTENT);
	}

	
	/* @DeleteMapping("/Forum/") public ResponseEntity<Forum>
	  deleteAllUsers(){
	 
	  forumDAO.deleteAllUsers(); return new
	  ResponseEntity<Forum>(HttpStatus.NO_CONTENT); }*/
	 

}

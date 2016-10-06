package com.sutta.collab.dao;

import java.util.List;

import com.sutta.collab.model.ForumPost;

public interface ForumPostDAO {
	
	public List<ForumPost> list();
	
	public ForumPost get(int id);
	
	public List<ForumPost> get(String forumId);
	
	public boolean save(ForumPost forumPost);
	
	public boolean update(ForumPost forumPost);
	
	public boolean delete(int id);
}

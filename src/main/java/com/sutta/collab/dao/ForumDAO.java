package com.sutta.collab.dao;

import java.util.List;

import com.sutta.collab.model.Forum;

public interface ForumDAO {
	
	public List<Forum> list();
	
	public Forum get(String id);
	
	public boolean save(Forum forum);
	
	public boolean update(Forum forum);
	
	public boolean delete(String id);
}

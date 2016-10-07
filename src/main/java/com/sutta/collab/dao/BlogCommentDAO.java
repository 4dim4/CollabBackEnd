package com.sutta.collab.dao;

import java.util.List;

import com.sutta.collab.model.BlogComment;

public interface BlogCommentDAO {
	
	public List<BlogComment> list();
	
	public BlogComment get(int id);
	
	public List<BlogComment> get(String blogId);
	
	public boolean save(BlogComment blogComment);
	
	public boolean update(BlogComment blogComment);
	
	public boolean delete(int id);
	
	public boolean delete(String blogId);
}

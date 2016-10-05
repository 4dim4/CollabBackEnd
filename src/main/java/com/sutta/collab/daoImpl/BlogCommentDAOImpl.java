package com.sutta.collab.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sutta.collab.dao.BlogCommentDAO;
import com.sutta.collab.model.BlogComment;

@Repository("blogCommentDAO")
public class BlogCommentDAOImpl implements BlogCommentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public BlogCommentDAOImpl(SessionFactory sessionFactory) {
		
		
		this.sessionFactory = sessionFactory;
		
	
	}
	
	

	@Transactional
	public List<BlogComment> list() {
		
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<BlogComment> list = sessionFactory.getCurrentSession().createCriteria(BlogComment.class)
				          .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		return list;
	}
	@Transactional
	public BlogComment get(String id) {
		
		String hql = "from BlogComment where id = '" + id + "'";
		
		@SuppressWarnings("unchecked")
		Query<BlogComment> query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List<BlogComment> listBlogComment = query.getResultList();
		
		if(listBlogComment != null && !listBlogComment.isEmpty()) {
			
			return listBlogComment.get(0);
		}
	
		return null;
	}
	



	@Transactional
	public boolean delete(String id) {
	  
	
      BlogComment deleteBlogComment = new BlogComment();
      
      deleteBlogComment.setId(id);
      
      try {
		sessionFactory.getCurrentSession().delete(deleteBlogComment);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
      
     
      return true;
		
	}



	@Transactional
	public boolean save(BlogComment blogComment) {
	
		
		try {
			sessionFactory.getCurrentSession().save(blogComment);
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	
		return true;
	}


	@Transactional
	public boolean update(BlogComment blogComment) {

		try {
			sessionFactory.getCurrentSession().update(blogComment);
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	
		return true;
	}

}
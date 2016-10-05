package com.sutta.collab.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sutta.collab.dao.ForumPostDAO;
import com.sutta.collab.model.ForumPost;

@Repository("forumPostDAO")
public class ForumPostDAOImpl implements ForumPostDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public ForumPostDAOImpl(SessionFactory sessionFactory) {
		
		
		this.sessionFactory = sessionFactory;
		
	
	}
	
	

	@Transactional
	public List<ForumPost> list() {
		
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<ForumPost> list = sessionFactory.getCurrentSession().createCriteria(ForumPost.class)
				          .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		return list;
	}
	@Transactional
	public ForumPost get(String id) {
		
		String hql = "from ForumPost where id = '" + id + "'";
		
		@SuppressWarnings("unchecked")
		Query<ForumPost> query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List<ForumPost> listForumPost = query.getResultList();
		
		if(listForumPost != null && !listForumPost.isEmpty()) {
			
			return listForumPost.get(0);
		}
	
		return null;
	}
	



	@Transactional
	public boolean delete(String id) {
	  
	
      ForumPost deleteForumPost = new ForumPost();
      
      deleteForumPost.setId(id);
      
      try {
		sessionFactory.getCurrentSession().delete(deleteForumPost);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
      
     
      return true;
		
	}



	@Transactional
	public boolean save(ForumPost forumPost) {
	
		
		try {
			sessionFactory.getCurrentSession().save(forumPost);
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	
		return true;
	}


	@Transactional
	public boolean update(ForumPost forumPost) {

		try {
			sessionFactory.getCurrentSession().update(forumPost);
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	
		return true;
	}

}

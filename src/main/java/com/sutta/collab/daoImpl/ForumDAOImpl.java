package com.sutta.collab.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sutta.collab.dao.ForumDAO;
import com.sutta.collab.model.Forum;

@Repository("forumDAO")
public class ForumDAOImpl implements ForumDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public ForumDAOImpl(SessionFactory sessionFactory) {
		
		
		this.sessionFactory = sessionFactory;
		
	
	}
	
	

	@Transactional
	public List<Forum> list() {
		
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Forum> list = sessionFactory.getCurrentSession().createCriteria(Forum.class)
				          .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		return list;
	}
	@Transactional
	public Forum get(String id) {
		
		String hql = "from Forum where id = '" + id + "'";
		
		@SuppressWarnings("unchecked")
		Query<Forum> query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List<Forum> listForum = query.getResultList();
		
		if(listForum != null && !listForum.isEmpty()) {
			
			return listForum.get(0);
		}
	
		return null;
	}
	



	@Transactional
	public boolean delete(String id) {
	  
	
      Forum deleteForum = new Forum();
      
      deleteForum.setId(id);
      
      try {
		sessionFactory.getCurrentSession().delete(deleteForum);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
      
     
      return true;
		
	}



	@Transactional
	public boolean save(Forum forum) {
	
		
		try {
			sessionFactory.getCurrentSession().save(forum);
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	
		return true;
	}


	@Transactional
	public boolean update(Forum forum) {

		try {
			sessionFactory.getCurrentSession().update(forum);
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	
		return true;
	}

}

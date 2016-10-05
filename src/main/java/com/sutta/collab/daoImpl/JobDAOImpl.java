package com.sutta.collab.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sutta.collab.dao.JobDAO;
import com.sutta.collab.model.Job;

@Repository("jobDAO")
public class JobDAOImpl implements JobDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public JobDAOImpl(SessionFactory sessionFactory) {
		
		
		this.sessionFactory = sessionFactory;
		
	
	}
	
	

	@Transactional
	public List<Job> list() {
		
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Job> list = sessionFactory.getCurrentSession().createCriteria(Job.class)
				          .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		return list;
	}
	@Transactional
	public Job get(String id) {
		
		String hql = "from Job where id = '" + id + "'";
		
		@SuppressWarnings("unchecked")
		Query<Job> query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List<Job> listJob = query.getResultList();
		
		if(listJob != null && !listJob.isEmpty()) {
			
			return listJob.get(0);
		}
	
		return null;
	}
	



	@Transactional
	public boolean delete(String id) {
	  
	
      Job deleteJob = new Job();
      
      deleteJob.setId(id);
      
      try {
		sessionFactory.getCurrentSession().delete(deleteJob);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
      
     
      return true;
		
	}



	@Transactional
	public boolean save(Job job) {
	
		
		try {
			sessionFactory.getCurrentSession().save(job);
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	
		return true;
	}


	@Transactional
	public boolean update(Job job) {

		try {
			sessionFactory.getCurrentSession().update(job);
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	
		return true;
	}

}

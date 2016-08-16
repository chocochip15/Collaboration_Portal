package com.niit.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.bean.UserDetails;
@Transactional
@EnableTransactionManagement
public class UserDetailsDAOImpl implements UserDetailsDAO{
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	public UserDetailsDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean save(UserDetails UserDetails) {
		try{sessionFactory.getCurrentSession().save(UserDetails);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}


}

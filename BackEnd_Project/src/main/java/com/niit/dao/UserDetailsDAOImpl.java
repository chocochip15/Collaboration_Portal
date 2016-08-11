package com.niit.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.niit.bean.UserDetails;

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

package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
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

	/*DAO method for saving and updating (editing) user details*/ 
	public boolean saveOrUpdate(UserDetails UserDetails) {
		try{sessionFactory.getCurrentSession().saveOrUpdate(UserDetails);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<UserDetails> list() {
		@SuppressWarnings("unchecked")
		List<UserDetails> listUserDetails = (List<UserDetails>) sessionFactory.getCurrentSession()
				.createCriteria(UserDetails.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listUserDetails;
	}
	/*get the user details on the basis of id */
	public UserDetails getByID(String id)
	{
		Session f =sessionFactory.getCurrentSession();
		UserDetails p =(UserDetails)f.load(UserDetails.class, new String(id));
		
		System.out.println(p.getName());
		return p;
	}
/*DAO method for deleting user details*/
public void deleteByID(String id) {
	
		Session f =sessionFactory.getCurrentSession();
		UserDetails p =(UserDetails)f.load(UserDetails.class, new String(id));		
		f.delete(p);
	}
}

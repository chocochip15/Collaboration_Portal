package com.niit.dao;

import java.util.List;

import com.niit.bean.UserDetails;

public interface UserDetailsDAO {
	public boolean saveOrUpdate(UserDetails UserDetails);
	public List<UserDetails> list();
	public void deleteByID(String id);
	public UserDetails getByID(String id);
	public boolean isUserExsit(UserDetails userdetails);

}

package com.epam.spring.movie.service.impl;

import java.util.List;


import com.epam.spring.movie.bean.User;
import com.epam.spring.movie.dao.UserDao;

import com.epam.spring.movie.service.UserService;


public class UserServiceImpl  implements UserService {
	
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public void create(User user) {
		userDao.create(user); 
		
	}

	@Override
	public void remove(User user) {
		userDao.remove(user);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public User getById(Integer id) {
		return userDao.getById(id);
	}

	@Override
	public List<User> getListByName(String name) {
		return userDao.getListByName(name);
	}
	


}

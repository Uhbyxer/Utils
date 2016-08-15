package com.epam.spring.movie.dao;





import com.epam.spring.movie.bean.User;

public interface UserDao extends BaseDao<User>, HasNameDao<User> {
		
	public User getUserByEmail(String email);
	
}

package com.epam.spring.movie.service;
import java.util.List;
import com.epam.spring.movie.bean.User;

public interface UserService {

	User getUserByEmail(String email);

	void create(User user);
	
	void remove(User user);
	
	List<User> getAll();
	
	User getById(Integer id);

	List<User> getListByName(String name);

}

package com.epam.spring.movie.dao.stub;

import com.epam.spring.movie.bean.User;
import com.epam.spring.movie.dao.UserDao;

public class UserDaoStub  extends BaseNamedDaoStub<User> implements UserDao {

	@Override
	public User getUserByEmail(String email) {
	
		return 	holder.entrySet()
				.stream()
				.filter(p -> p.getValue().getEmail().compareToIgnoreCase(email) == 0)
				.map(p -> p.getValue())
				.findFirst()
				.orElse(null);
	}

	
}

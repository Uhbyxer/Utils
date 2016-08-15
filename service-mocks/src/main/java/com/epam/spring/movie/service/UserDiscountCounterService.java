package com.epam.spring.movie.service;

import java.util.List;
import com.epam.spring.movie.bean.DiscountStrategy;
import com.epam.spring.movie.bean.User;
import com.epam.spring.movie.bean.UserDiscountCounter;

public interface UserDiscountCounterService {
	
	void create(UserDiscountCounter userDiscountCounter);

	void remove(UserDiscountCounter userDiscountCounter);

	List<UserDiscountCounter> getAll();
	
	UserDiscountCounter getById(Integer id);
	
	UserDiscountCounter getByUserAndDiscount(User user, DiscountStrategy discountStrategy);
	
	int incrementAndGetCount(User user, DiscountStrategy discountStrategy);
	
	int getTotalCountByDiscount(DiscountStrategy discountStrategy);
	
	void update(UserDiscountCounter userDiscountCounter); 
}

package com.epam.spring.movie.dao;

import com.epam.spring.movie.bean.DiscountStrategy;


import com.epam.spring.movie.bean.User;
import com.epam.spring.movie.bean.UserDiscountCounter;

public interface UserDiscountCounterDao extends BaseDao<UserDiscountCounter>{
	
	public UserDiscountCounter getByUserAndDiscount(User user, DiscountStrategy discountStrategy);
	
	public int incrementAndGetCount(User user, DiscountStrategy discountStrategy);
	
	public int getTotalCountByDiscount(DiscountStrategy discountStrategy);

	public void update(UserDiscountCounter userDiscountCounter);
	
}

package com.epam.spring.movie.service.impl;

import java.util.List;

import com.epam.spring.movie.bean.DiscountStrategy;
import com.epam.spring.movie.bean.User;
import com.epam.spring.movie.bean.UserDiscountCounter;
import com.epam.spring.movie.dao.UserDiscountCounterDao;
import com.epam.spring.movie.service.UserDiscountCounterService;

public class UserDiscountCounterServiceImpl implements UserDiscountCounterService {

	private UserDiscountCounterDao userDiscountCounterDao;
	
	public void setUserDiscountCounterDao(UserDiscountCounterDao userDiscountCounterDao) {
		this.userDiscountCounterDao = userDiscountCounterDao;
	}

	@Override
	public void create(UserDiscountCounter userDiscountCounter) {
		userDiscountCounterDao.create(userDiscountCounter);
	}

	@Override
	public void remove(UserDiscountCounter userDiscountCounter) {
		userDiscountCounterDao.remove(userDiscountCounter);
	}

	@Override
	public List<UserDiscountCounter> getAll() {
		return userDiscountCounterDao.getAll();
	}

	@Override
	public UserDiscountCounter getById(Integer id) {
		return userDiscountCounterDao.getById(id);
	}

	@Override
	public UserDiscountCounter getByUserAndDiscount(User user, DiscountStrategy discountStrategy) {
		return userDiscountCounterDao.getByUserAndDiscount(user, discountStrategy);
	}

	@Override
	public int incrementAndGetCount(User user, DiscountStrategy discountStrategy) {
		return userDiscountCounterDao.incrementAndGetCount(user, discountStrategy);
	}

	@Override
	public int getTotalCountByDiscount(DiscountStrategy discountStrategy) {
		return userDiscountCounterDao.getTotalCountByDiscount(discountStrategy);
	}

	@Override
	public void update(UserDiscountCounter userDiscountCounter) {
		userDiscountCounterDao.update(userDiscountCounter);
	}

}

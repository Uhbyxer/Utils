package com.epam.spring.movie.dao.stub;

import java.util.HashMap;

import com.epam.spring.movie.bean.DiscountStrategy;
import com.epam.spring.movie.bean.User;
import com.epam.spring.movie.bean.UserDiscountCounter;
import com.epam.spring.movie.dao.UserDiscountCounterDao;

public class UserDiscountCounterDaoStub extends BaseDaoStub<UserDiscountCounter> implements UserDiscountCounterDao {

	@Override
	public UserDiscountCounter getByUserAndDiscount(User user, DiscountStrategy discountStrategy) {
		if(user == null || discountStrategy == null) return null;
		
		UserDiscountCounter res = holder.entrySet()
				.stream()
				.filter(p -> user.getId().equals(p.getValue().getUser().getId()))
				.filter(p -> discountStrategy.getName().equals(p.getValue().getDiscountStrategy().getName()))
				.map(p -> p.getValue())
				.findFirst()
				.orElse(null);
		
		
		return res;		
	}
	
	public UserDiscountCounter getByEventOrCreate(User user,  DiscountStrategy discountStrategy) {
		
		UserDiscountCounter discountCounter  = getByUserAndDiscount(user, discountStrategy);
		if(discountCounter == null) {
			discountCounter = new UserDiscountCounter();
			discountCounter.setUser(user);
			discountCounter.setDiscountStrategy(discountStrategy);
			
			create(discountCounter);
		}
		return discountCounter;
	}

	@Override
	public int incrementAndGetCount(User user, DiscountStrategy discountStrategy) {
		UserDiscountCounter discountCounter = getByEventOrCreate(user, discountStrategy);
		discountCounter.setCount(discountCounter.getCount() + 1);
		return discountCounter.getCount();
	}
	
	@Override
	public int getTotalCountByDiscount(DiscountStrategy discountStrategy) {
		if(discountStrategy == null) return 0;
		
		int res = holder.entrySet()
				.stream()
				.filter(p -> discountStrategy.getName().equals(p.getValue().getDiscountStrategy().getName()))
				.mapToInt(p -> p.getValue().getCount())
				.sum();
				
		return res;
		
	}
	
	public UserDiscountCounterDaoStub() {
		holder = new HashMap<>();
	}

	@Override
	public void update(UserDiscountCounter userDiscountCounter) {
		
	}	
	
	
}

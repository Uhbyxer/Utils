package com.epam.spring.movie.bean;

public class UserDiscountCounter extends BaseBean {
	
	private User user;
	
	private DiscountStrategy discountStrategy;
	
	private int count;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DiscountStrategy getDiscountStrategy() {
		return discountStrategy;
	}

	public void setDiscountStrategy(DiscountStrategy discountStrategy) {
		this.discountStrategy = discountStrategy;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder("Discount: ");
		
		if(discountStrategy != null) {
			res.append(discountStrategy.getName()).append(" ");
		} 
		if(user != null) {
			res.append("for ").append(user.getName()).append(" ");
		}
		res.append("count = ").append(count);
		
		return res.toString();
	}
	
}

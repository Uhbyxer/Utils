package com.epam.spring.movie.service.impl;

import java.util.List;
import com.epam.spring.movie.bean.DiscountStrategy;
import com.epam.spring.movie.bean.Ticket;
import com.epam.spring.movie.dao.DiscountStrategyDao;
import com.epam.spring.movie.service.DiscountStrategyService;


public class DiscountStrategyServiceImpl implements DiscountStrategyService {

	private DiscountStrategyDao discountStrategyDao;
	
	public void setDiscountStrategyDao(DiscountStrategyDao discountStrategyDao) {
		this.discountStrategyDao = discountStrategyDao;
	}
	

	@Override
	public void create(DiscountStrategy discountStrategy) {
		discountStrategyDao.create(discountStrategy);
		
	}

	@Override
	public void remove(DiscountStrategy discountStrategy) {
		discountStrategyDao.remove(discountStrategy);
		
	}

	@Override
	public List<DiscountStrategy> getAll() {
		return discountStrategyDao.getAll();
	}

	@Override
	public DiscountStrategy getById(Integer id) {
		
		return discountStrategyDao.getById(id);
	}


	@Override
	public List<DiscountStrategy> getListByName(String name) {
		return discountStrategyDao.getListByName(name);
	}



	@Override
	public DiscountStrategy getBestDiscountStrategy(Ticket ticket, long countOfTicketsForUser) {
		
		DiscountStrategy res = null;
		
		if(ticket == null || ticket.getUser() == null)
			return res;
		
		double max = 0;
		for(DiscountStrategy discount : getAll()) {
			
			double currentDiscount = discount.calculateDiscount(ticket, countOfTicketsForUser);
			if(currentDiscount > max) {
				max = currentDiscount;
				res = discount;
			}
			
		}
		
		ticket.setDiscountStrategy(res);
		ticket.setDiscount(max);
		
		return res;
	} 

	
	
}

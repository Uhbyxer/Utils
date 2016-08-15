package com.epam.spring.movie.service;


import java.util.List;
import com.epam.spring.movie.bean.DiscountStrategy;
import com.epam.spring.movie.bean.Ticket;


public interface DiscountStrategyService {
	
	void create(DiscountStrategy discountStrategy);

	void remove(DiscountStrategy discountStrategy);

	List<DiscountStrategy> getAll();
	
	DiscountStrategy getById(Integer id);

	List<DiscountStrategy> getListByName(String name);

	DiscountStrategy getBestDiscountStrategy(Ticket ticket, long countOfTicketsForUser);

}

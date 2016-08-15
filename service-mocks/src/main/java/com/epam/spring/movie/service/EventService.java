package com.epam.spring.movie.service;

import java.util.List;

import com.epam.spring.movie.bean.Event;


public interface EventService {
	
	void create(Event event);

	void remove(Event event);

	List<Event> getAll();
	
	Event getById(Integer id);

	List<Event> getListByName(String name);
	
	Event getByName(String name);
	
	double getPrice(Event event);
	
	List<Event> getEventsForPage(int pageNumber, int rowsPerPage);
	
	int getCount();
	
}	

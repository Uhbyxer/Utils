package com.epam.spring.movie.service;

import java.util.List;

import com.epam.spring.movie.bean.Event;
import com.epam.spring.movie.bean.EventCounter;

public interface EventCounterService {
	
	void create(EventCounter eventCounter);

	void remove(EventCounter eventCounter);

	List<EventCounter> getAll();
	
	EventCounter getById(Integer id);
	
	EventCounter getByEvent(Event event);
	
	int incrementAndGetByNameCount(Event event);
	
	int incrementAndGetPriceCount(Event event);

	int incrementAndGetBookCount(Event event);
	
	void update(EventCounter eventCounter);

}

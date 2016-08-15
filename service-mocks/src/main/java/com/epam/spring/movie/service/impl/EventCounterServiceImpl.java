package com.epam.spring.movie.service.impl;

import java.util.List;

import com.epam.spring.movie.bean.Event;
import com.epam.spring.movie.bean.EventCounter;
import com.epam.spring.movie.dao.EventCounterDao;
import com.epam.spring.movie.service.EventCounterService;

public class EventCounterServiceImpl implements EventCounterService {

	private EventCounterDao eventCounterDao;
	
	public void setEventCounterDao(EventCounterDao eventCounterDao) {
		this.eventCounterDao = eventCounterDao;
	}

	@Override
	public void create(EventCounter eventCounter) {
		eventCounterDao.create(eventCounter);
	}

	@Override
	public void remove(EventCounter eventCounter) {
		eventCounterDao.remove(eventCounter);
	}

	@Override
	public List<EventCounter> getAll() {
		return eventCounterDao.getAll();
	}

	@Override
	public EventCounter getById(Integer id) {
		return eventCounterDao.getById(id);
	}

	@Override
	public EventCounter getByEvent(Event event) {
		return eventCounterDao.getByEvent(event);
	}

	@Override
	public int incrementAndGetByNameCount(Event event) {
		return eventCounterDao.incrementAndGetByNameCount(event);
	}

	@Override
	public int incrementAndGetPriceCount(Event event) {
		return eventCounterDao.incrementAndGetPriceCount(event);
	}

	@Override
	public int incrementAndGetBookCount(Event event) {
		return eventCounterDao.incrementAndGetBookCount(event);
	}

	@Override
	public void update(EventCounter eventCounter) {
		eventCounterDao.update(eventCounter);
	}

}

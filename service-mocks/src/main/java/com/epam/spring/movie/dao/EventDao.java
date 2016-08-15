package com.epam.spring.movie.dao;

import java.util.List;

import com.epam.spring.movie.bean.Event;


public interface EventDao extends BaseDao<Event>, HasNameDao<Event> {
	public Event getByName(String name);

	public double getPrice(Event event);

	public List<Event> getEventsForPage(int pageNumber, int rowsPerPage);

	public int getCount();
}

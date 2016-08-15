package com.epam.spring.movie.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.epam.spring.movie.bean.Event;
import com.epam.spring.movie.bean.EventCounter;
import com.epam.spring.movie.dao.EventCounterDao;
import com.epam.spring.movie.dao.EventDao;


public class EventCounterDaoImpl extends  BaseDaoImpl<EventCounter> implements EventCounterDao {
	
	private static final String INSERT_RECORD = "insert into event_counter (event_id, by_name_count, price_count, book_count) VALUES (?,?,?,?)";
	
	private static final String UPDATE_RECORD = 
			"update event_counter set event_id = ?, by_name_count = ?, price_count = ?, book_count = ? where id = ?";
	
	private static final String GET_BY_EVENT = "select * from event_counter where event_id = ?"; 

	private EventDao eventDao;
	
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	@Override
	protected EventCounter getBeanFromResultSet(ResultSet rs) throws SQLException {
		EventCounter eventCounter = new EventCounter();
		eventCounter.setId(rs.getInt("id"));
		eventCounter.setBookCount(rs.getInt("book_count"));
		eventCounter.setPriceCount(rs.getInt("price_count"));
		eventCounter.setByNameCount(rs.getInt("by_name_count"));
		eventCounter.setEvent(eventDao.getById(rs.getInt("event_id")));
		
		return eventCounter;
	}
	

	@Override
	public EventCounter getByEvent(Event event) {
		try {

			return jdbcTemplate.queryForObject(GET_BY_EVENT, new Object[] { event.getId() },

			new RowMapper<EventCounter>() {
				public EventCounter mapRow(ResultSet rs, int rowNum) throws SQLException {

					return getBeanFromResultSet(rs);

				}
			});

		} catch (Exception e) {
			
			// no rows
			return null;
		}
	}
	
	public EventCounter getByEventOrCreate(Event event) {
		
		EventCounter eventCounter = getByEvent(event);
		if(eventCounter == null) {
			eventCounter = new EventCounter();
			eventCounter.setEvent(event);
			
			create(eventCounter);
		}
		return eventCounter;
	}
	
	@Override
	public int incrementAndGetByNameCount(Event event) {
		EventCounter eventCounter = getByEventOrCreate(event);
		eventCounter.setByNameCount(eventCounter.getByNameCount() + 1);
		update(eventCounter);
		return eventCounter.getByNameCount();
	}

	@Override
	public int incrementAndGetPriceCount(Event event) {
		EventCounter eventCounter = getByEventOrCreate(event);
		eventCounter.setPriceCount(eventCounter.getPriceCount() + 1);
		update(eventCounter);
		return eventCounter.getPriceCount();
	}

	@Override
	public int incrementAndGetBookCount(Event event) {
		EventCounter eventCounter = getByEventOrCreate(event);
		eventCounter.setBookCount(eventCounter.getBookCount() + 1);
		update(eventCounter);
		return eventCounter.getBookCount();
	}

	@Override
	public void create(EventCounter eventCounter) {

		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				connection -> {
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RECORD,
							Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setInt(1, eventCounter.getEvent().getId());
					preparedStatement.setInt(2, eventCounter.getByNameCount());
					preparedStatement.setInt(3, eventCounter.getPriceCount());
					preparedStatement.setInt(4, eventCounter.getBookCount());
					return preparedStatement;

				}, holder);

		eventCounter.setId(holder.getKey().intValue());
	}
	
	@Override
	public void update(EventCounter eventCounter) {
		jdbcTemplate.update(
				UPDATE_RECORD,
				eventCounter.getEvent().getId(),
				eventCounter.getByNameCount(),
				eventCounter.getPriceCount(),
				eventCounter.getBookCount(),
				eventCounter.getId()
		);
		
	}
	

	public EventCounterDaoImpl() {
		super("event_counter");
	}

}

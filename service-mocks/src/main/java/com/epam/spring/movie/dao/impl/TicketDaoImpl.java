package com.epam.spring.movie.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.epam.spring.movie.bean.Auditorium;
import com.epam.spring.movie.bean.Event;
import com.epam.spring.movie.bean.Ticket;
import com.epam.spring.movie.bean.User;
import com.epam.spring.movie.dao.AuditoriumDao;
import com.epam.spring.movie.dao.DiscountStrategyDao;
import com.epam.spring.movie.dao.EventDao;
import com.epam.spring.movie.dao.TicketDao;
import com.epam.spring.movie.dao.UserDao;

public class TicketDaoImpl extends BaseDaoImpl<Ticket> implements TicketDao {
	
	private static final String INSERT_RECORD 
		= "insert into ticket (event_id, auditorium_id, user_id, strategy_id, seat, ticket_datetime, price_base, price_rait, price_vip, discount, price) " +
		  "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String GET_BY_USER = "select * from ticket where user_id = ?";
	private static final String GET_BY_EVENT_AND_DATETIME = "select * from ticket where event_id = ? and ticket_datetime = ?";
	private static final String GET_COUNT_BY_USER = "select count(*) from ticket where user_id = ?";
	private static final String GET_BY_DATETIME_AND_AUDITORIUM_AND_SEAT = 
			"select count(*) from ticket where ticket_datetime = ? and auditorium_id = ? and seat = ?"; 
	
	
	private EventDao eventDao;
	private AuditoriumDao auditoriumDao;
	private UserDao userDao;
	private DiscountStrategyDao discountStrategyDao;
	
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	public void setAuditoriumDao(AuditoriumDao auditoriumDao) {
		this.auditoriumDao = auditoriumDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setDiscountStrategyDao(DiscountStrategyDao discountStrategyDao) {
		this.discountStrategyDao = discountStrategyDao;
	}

	@Override
	public void create(Ticket ticket) {
		
		jdbcTemplate.update(
				INSERT_RECORD,
				ticket.getEvent().getId(),
				ticket.getAuditorium().getId(),
				(ticket.getUser() == null ? null : ticket.getUser().getId()),
				(ticket.getDiscountStrategy() == null ? null : ticket.getDiscountStrategy().getId()),
				ticket.getSeat(),
				java.util.Date.from(ticket.getDateTime().atZone(ZoneId.systemDefault()).toInstant()),
				ticket.getPriceBase(),
				ticket.getPriceWithRaiting(),
				ticket.getPriceWithVip(),
				ticket.getDiscount(),
				ticket.getPrice()
		);
	}
	
	@Override
	protected Ticket getBeanFromResultSet(ResultSet rs) throws SQLException {
		
		Ticket ticket = new Ticket();
		
		ticket.setId(rs.getInt("id"));
		ticket.setEvent(eventDao.getById(rs.getInt("event_id"))); 
		ticket.setAuditorium(auditoriumDao.getById(rs.getInt("auditorium_id")));
		
		if(rs.getObject("user_id") == null) 
			ticket.setUser(null);
		else	
			ticket.setUser(userDao.getById(rs.getInt("user_id")));
		
		ticket.setDiscountStrategy(discountStrategyDao.getById(rs.getInt("strategy_id")));
		ticket.setSeat(rs.getInt("seat"));
		
		if(rs.getObject("ticket_datetime") != null) {
			try {
				ticket.setDate(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(String.valueOf(rs.getString("ticket_datetime"))));
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		ticket.setPriceBase(rs.getDouble("price_base")); 
		ticket.setPriceWithRaiting(rs.getDouble("price_rait"));
		ticket.setPriceWithVip(rs.getDouble("price_vip"));
		ticket.setDiscount(rs.getDouble("discount"));
		ticket.setPrice(rs.getDouble("price"));
		
		return ticket;
	}

	@Override
	public List<Ticket> getTicketsForEvent(Event event, LocalDateTime dateTime) {
		
		return  jdbcTemplate.query(GET_BY_EVENT_AND_DATETIME, new Object[] { event.getId(), java.util.Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()) }, new RowMapper<Ticket>() {
			
			public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {

				return getBeanFromResultSet(rs);
				
			}

		});	
	}

	@Override
	public List<Ticket> getTicketsForUser(User user) {
		
		return  jdbcTemplate.query(GET_BY_USER, new Object[] { user.getId() }, new RowMapper<Ticket>() {
			
			public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {

				return getBeanFromResultSet(rs);
				
			}

		});	
	}

	@Override
	public long getCountOfTicketsForUser(User user) {
		
		return jdbcTemplate.queryForObject(
				GET_COUNT_BY_USER,Long.class, 
				user.getId());
	}

	@Override
	public boolean isBooked(LocalDateTime dateTime, Auditorium auditorium, Integer seat) {
		
		return jdbcTemplate.queryForObject(
				GET_BY_DATETIME_AND_AUDITORIUM_AND_SEAT, Integer.class, 
				java.util.Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()),
				auditorium.getId(),
				seat) > 0;
	}
	
	public TicketDaoImpl() {
		super("ticket");
	}

}

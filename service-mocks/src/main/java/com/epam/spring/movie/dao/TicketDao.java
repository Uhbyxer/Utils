package com.epam.spring.movie.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.epam.spring.movie.bean.Auditorium;
import com.epam.spring.movie.bean.Event;
import com.epam.spring.movie.bean.Ticket;
import com.epam.spring.movie.bean.User;

public interface TicketDao extends BaseDao<Ticket> {
	
	public List<Ticket> getTicketsForEvent(Event event, LocalDateTime dateTime);
	
	public List<Ticket> getTicketsForUser(User user);
	
	public long getCountOfTicketsForUser(User user);
	
	public boolean isBooked(LocalDateTime dateTime, Auditorium auditorium, Integer seat);
	
}

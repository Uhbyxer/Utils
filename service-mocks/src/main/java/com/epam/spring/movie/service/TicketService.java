package com.epam.spring.movie.service;

import java.time.LocalDateTime;
import java.util.List;

import com.epam.spring.movie.bean.Auditorium;
import com.epam.spring.movie.bean.Event;
import com.epam.spring.movie.bean.Ticket;
import com.epam.spring.movie.bean.User;


public interface TicketService {
	
	List<Ticket> getTicketsForEvent(Event event, LocalDateTime dateTime);

	void create(Ticket ticket);

	void remove(Ticket ticket);

	List<Ticket> getAll();

	Ticket getById(Integer id);
	
	long getCountOfTicketsForUser(User user);

	void calculatePrice(Ticket ticket);

	boolean isBooked(LocalDateTime dateTime, Auditorium auditorium, Integer seat);
	
	List<Ticket> getTicketsForUser(User user);
	
	void bookTicket(Ticket ticket);
		
}

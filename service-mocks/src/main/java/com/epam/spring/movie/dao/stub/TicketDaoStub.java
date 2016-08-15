package com.epam.spring.movie.dao.stub;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import com.epam.spring.movie.bean.Auditorium;
import com.epam.spring.movie.bean.Event;
import com.epam.spring.movie.bean.Ticket;
import com.epam.spring.movie.bean.User;
import com.epam.spring.movie.dao.TicketDao;

public class TicketDaoStub extends BaseDaoStub<Ticket> implements TicketDao  {

	@Override
	public List<Ticket> getTicketsForEvent(Event event, LocalDateTime dateTime) {
		return  holder.entrySet()
				.stream()
				.filter(p -> p.getValue().getEvent().equals(event))
				.filter(p -> p.getValue().getDateTime().equals(dateTime))
				.map(p -> p.getValue())
				.collect(Collectors.toList());
	}
	

	@Override
	public List<Ticket> getTicketsForUser(User user) {
		if(user == null) return new ArrayList<Ticket>();
		
		return  holder.entrySet()
				.stream()
				.filter(p -> user.equals(p.getValue().getUser()))
				.map(p -> p.getValue())
				.collect(Collectors.toList());	
	}
	
	
	@Override
	public long getCountOfTicketsForUser(User user) {

		if(user == null) return 0;
		
		return 	holder.entrySet()
				.stream()
				.filter(p -> user.equals(p.getValue().getUser()))
				.count();

	
	}


	@Override
	public boolean isBooked(LocalDateTime dateTime, Auditorium auditorium, Integer seat) {
		
		if(dateTime == null || auditorium == null) return true;
		
	 	return 	holder.entrySet()
	 			.stream()
	 			.filter(p -> dateTime.equals(p.getValue().getDateTime()))
	 			.filter(p -> auditorium.equals(p.getValue().getAuditorium()))
	 			.filter(p -> seat.equals(p.getValue().getSeat()))
	 			.count() > 0;
		
	}


	

}

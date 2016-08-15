package com.epam.spring.movie.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.epam.spring.movie.bean.Auditorium;
import com.epam.spring.movie.bean.DiscountStrategy;
import com.epam.spring.movie.bean.Event;
import com.epam.spring.movie.bean.Ticket;
import com.epam.spring.movie.bean.User;
import com.epam.spring.movie.dao.TicketDao;
import com.epam.spring.movie.exception.BookException;
import com.epam.spring.movie.service.DiscountStrategyService;
import com.epam.spring.movie.service.TicketService;

public class TicketServiceImpl implements TicketService {

	private TicketDao ticketDao;
	
	private DiscountStrategyService discountStrategyService;
	
	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}
	
	public void setDiscountStrategyService(DiscountStrategyService discountStrategyService) {
		this.discountStrategyService = discountStrategyService;
	}

	@Override
	public List<Ticket> getTicketsForEvent(Event event, LocalDateTime dateTime) {
		return ticketDao.getTicketsForEvent(event, dateTime);
	}

	@Override
	public void create(Ticket ticket) {
		ticketDao.create(ticket);
		
	}

	@Override
	public void remove(Ticket ticket) {
		ticketDao.remove(ticket);
		
	}

	@Override
	public List<Ticket> getAll() {
		return ticketDao.getAll();
	}

	@Override
	public Ticket getById(Integer id) {
		return ticketDao.getById(id);
	}

	@Override
	public long getCountOfTicketsForUser(User user) {
		return ticketDao.getCountOfTicketsForUser(user);
	}

	
	
	@Override
	public void calculatePrice(Ticket ticket) {
		
		ticket.clearPricesAndDiscount();
		
		if(ticket.getEvent() == null) return;
		
		double price = ticket.getEvent().getPrice();
		ticket.setPriceBase(price);
		
		price +=  ((double) ticket.getEvent().getRating()) / 10.0 * price;
		ticket.setPriceWithRaiting(price);
		
		Auditorium auditorium = ticket.getAuditorium();
		if(auditorium != null) {
			if (auditorium.getVipSeats() != null && auditorium.getVipSeats().contains(ticket.getSeat())) {
				price *= 2;
			}
		}
		ticket.setPriceWithVip(price);
		
		if(ticket.getUser() != null)
			discountStrategyService.getBestDiscountStrategy(ticket, getCountOfTicketsForUser(ticket.getUser()));
				
		DiscountStrategy discountStrategy = ticket.getDiscountStrategy();
		if(discountStrategy != null) {
			ticket.setDiscountStrategy(discountStrategy);
			double discount = discountStrategy.getValue();
			price *= (100.0 - discount) / 100 ;
			
			ticket.setDiscount(discount);
		}
		
		ticket.setPrice(price);
	}



	@Override
	public boolean isBooked(LocalDateTime dateTime, Auditorium auditorium, Integer seat) {
		return ticketDao.isBooked(dateTime, auditorium, seat);
	}

	@Override
	public List<Ticket> getTicketsForUser(User user) {
		return ticketDao.getTicketsForUser(user);
	}

	@Override
	public void bookTicket(Ticket ticket) {

		if(isBooked(ticket.getDateTime(), ticket.getAuditorium(), ticket.getSeat())) {
			throw new BookException("BOOKED ALREADY !!!");
			
		} else {
			calculatePrice(ticket);
			create(ticket);
		}
		
		
	}


	
	

}

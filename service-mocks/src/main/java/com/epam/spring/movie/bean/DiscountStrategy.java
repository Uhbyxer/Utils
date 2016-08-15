package com.epam.spring.movie.bean;

public abstract class DiscountStrategy extends NamedBean {
	
	public static final String EVERY_TICKET_DISCOUNT = "every-ticket-discount";
	
	public static final String BIRTHDAY_DISCOUNT = "birthday-discount";
	
	public abstract double getValue(); 
	
	public abstract double calculateDiscount(Ticket ticket, long countOfTicketsForUser);
	
}

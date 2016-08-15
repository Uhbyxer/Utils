package com.epam.spring.movie.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BirthdayDiscountStrategy extends DiscountStrategy {
	
	private double mainDiscount;
	

	public double getMainDiscount() {
		return mainDiscount;
	}

	public void setMainDiscount(double mainDiscount) {
		this.mainDiscount = mainDiscount;
	}	
	
	@Override
	public String toString() {
		return name + ": " + mainDiscount + " %";
	}
	
	@Override
	public double getValue() {
		return mainDiscount;
	}

	@Override
	public double calculateDiscount(Ticket ticket, long countOfTicketsForUser) {
		LocalDateTime eventDate = ticket.getDateTime();
		LocalDate userDate = ticket.getUser().getBirth();
		
		if(eventDate != null && userDate != null) {
			if(eventDate.getMonthValue() == userDate.getMonthValue() 
					&& eventDate.getDayOfMonth() == userDate.getDayOfMonth()) {
				
				return getMainDiscount();
			}
		}		
		return 0;
	}

}

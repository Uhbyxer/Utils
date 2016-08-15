package com.epam.spring.movie.bean;

public class EveryTicketDiscountStrategy extends DiscountStrategy {
	
	private double everyTicketDiscount;
	
	private int everyTicketNumber;

	public double getEveryTicketDiscount() {
		return everyTicketDiscount;
	}

	public void setEveryTicketDiscount(double everyTicketDiscount) {
		this.everyTicketDiscount = everyTicketDiscount;
	}

	public int getEveryTicketNumber() {
		return everyTicketNumber;
	}

	public void setEveryTicketNumber(int everyTicketNumber) {
		this.everyTicketNumber = everyTicketNumber;
	}
	
	@Override
	public String toString() {
		return name + ": " + everyTicketDiscount + " % per " + everyTicketNumber + " ticket";
	}
	
	@Override
	public double getValue() {
		return everyTicketDiscount;
	}

	@Override
	public double calculateDiscount(Ticket ticket, long countOfTicketsForUser) {
		double count = 1.0 + countOfTicketsForUser;
		if (count % getEveryTicketNumber() == 0) {
			return getEveryTicketDiscount();
		}
		return 0;
	}

}

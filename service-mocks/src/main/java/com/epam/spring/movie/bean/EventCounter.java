package com.epam.spring.movie.bean;

public class EventCounter extends BaseBean {
	
	private Event event;
	
	private int byNameCount;
	
	private int priceCount;
	
	private int bookCount;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public int getByNameCount() {
		return byNameCount;
	}

	public void setByNameCount(int byNameCount) {
		this.byNameCount = byNameCount;
	}

	public int getPriceCount() {
		return priceCount;
	}

	public void setPriceCount(int priceCount) {
		this.priceCount = priceCount;
	}

	public int getBookCount() {
		return bookCount;
	}

	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}

	@Override
	public String toString() {
		return "Counter for " + (event == null ? "nullEvent": "event id:" + event.getId() + " name:" + event.getName()) + ": byNameCount=" + byNameCount + ", priceCount=" + priceCount
				+ ", bookCount=" + bookCount;
	}
	
	
}

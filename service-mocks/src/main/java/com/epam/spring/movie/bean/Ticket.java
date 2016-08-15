package com.epam.spring.movie.bean;


import java.time.LocalDateTime;
import java.time.ZoneId;



public class Ticket extends BaseBean implements Cloneable{
	
	private Event event;
	
	private LocalDateTime dateTime;
	
	private Auditorium auditorium;
	
	private Integer seat;
	
	private User user;
	
	private Double priceBase;
	
	private Double priceWithRaiting;
	
	private Double priceWithVip;
	
	private DiscountStrategy discountStrategy;
	
	private Double discount;
	
	private Double price;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	public void setDate(java.util.Date date) {
		dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
	
	public void setHour(int hour) {
		dateTime = dateTime.withHour(hour);
	}
	
	public void setMinute(int minute) {
		dateTime = dateTime.withMinute(minute);
	}
	
	public Auditorium getAuditorium() {
		return auditorium;
	}

	public void setAuditorium(Auditorium auditorium) {
		this.auditorium = auditorium;
	}

	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Double getPriceBase() {
		return priceBase;
	}

	public void setPriceBase(Double priceBase) {
		this.priceBase = priceBase;
	}

	public Double getPriceWithRaiting() {
		return priceWithRaiting;
	}

	public void setPriceWithRaiting(Double priceWithRaiting) {
		this.priceWithRaiting = priceWithRaiting;
	}

	public Double getPriceWithVip() {
		return priceWithVip;
	}

	public void setPriceWithVip(Double priceWithVip) {
		this.priceWithVip = priceWithVip;
	}

	public DiscountStrategy getDiscountStrategy() {
		return discountStrategy;
	}

	public void setDiscountStrategy(DiscountStrategy discountStrategy) {
		this.discountStrategy = discountStrategy;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}


	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return "" + event.getName() + "/" + auditorium.getName() + "/" + dateTime + "/seat: " + seat 
				+ (user == null ? "" : " by " + user.getEmail()
				+ (price == null ? "" : " $ " + price));
	}
	
	public String getBillDetails() {
		StringBuffer res = new StringBuffer();
		
		if(priceBase != null) {
			res.append("Base: ").append(priceBase).append("\n");
		}
		
		if(priceWithRaiting != null) {
			res.append("Rating: ").append(priceWithRaiting).append("\n");
		}
		
		if(priceWithVip != null) {
			res.append("VIP: ").append(priceWithVip).append("\n");
		}
		
		if(discountStrategy != null) {
			res.append("Discount strategy: ").append(discountStrategy).append("\n");
		} else {
			res.append("No discount strategy\n");
		}
		
		if(discount != null) {
			res.append("Discount: ").append(discount).append(" % \n");
		}
		
		if(price != null) {
			res.append("FINAL PRICE: ").append(price).append("\n");
		}
		
		return res.toString();
	}
	
	public void clearPricesAndDiscount() {
		setPrice(0.0);
		setPriceBase(0.0);
		setPriceWithRaiting(0.0);
		setPriceWithVip(0.0);
		setDiscountStrategy(null);
	}

}

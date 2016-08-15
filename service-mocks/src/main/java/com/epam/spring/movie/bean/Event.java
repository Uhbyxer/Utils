package com.epam.spring.movie.bean;

public class Event extends NamedBean {
	
	private int rating;
	
	private double price;
	
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return name +"( id = " + id  + ") < Raiting: " + rating + "; Base price: $" + price + " >";
	}

		
	
}

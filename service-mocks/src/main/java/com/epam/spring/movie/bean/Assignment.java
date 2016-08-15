package com.epam.spring.movie.bean;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Assignment extends BaseBean {
	
	private Event event;
	
	private LocalDateTime dateTime;
	
	private Auditorium auditorium;

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
	
	@Override
	public String toString() {
		return (auditorium == null ? "null" : auditorium.getName())
				+ "[" +  (event == null ? "null" : event.getName())  + "] on " + dateTime;
	}
	
}

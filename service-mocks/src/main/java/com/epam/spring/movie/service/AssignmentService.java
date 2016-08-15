package com.epam.spring.movie.service;

import java.time.LocalDateTime;
import java.util.List;

import com.epam.spring.movie.bean.Assignment;
import com.epam.spring.movie.bean.Auditorium;
import com.epam.spring.movie.bean.Event;


public interface AssignmentService {

	boolean isAssigned(Auditorium auditorium, LocalDateTime dateTime);

	void create(Assignment assignment);
	
	void remove(Assignment assignment);

	List<Assignment> getAll();

	Assignment getById(Integer id);
	
	List<Assignment> getForEvent(Event event);

}

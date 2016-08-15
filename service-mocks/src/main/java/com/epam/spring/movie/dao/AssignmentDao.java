package com.epam.spring.movie.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.epam.spring.movie.bean.Assignment;
import com.epam.spring.movie.bean.Auditorium;
import com.epam.spring.movie.bean.Event;


public interface AssignmentDao extends BaseDao<Assignment> {
	
	boolean isAssigned(Auditorium auditorium, LocalDateTime dateTime);

	List<Assignment> getForEvent(Event event);

}

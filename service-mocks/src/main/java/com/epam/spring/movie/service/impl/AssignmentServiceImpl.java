package com.epam.spring.movie.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.epam.spring.movie.bean.Assignment;
import com.epam.spring.movie.bean.Auditorium;
import com.epam.spring.movie.bean.Event;
import com.epam.spring.movie.dao.AssignmentDao;
import com.epam.spring.movie.service.AssignmentService;

public class AssignmentServiceImpl implements AssignmentService {

	private AssignmentDao assignmentDao;
	
	public void setAssignmentDao(AssignmentDao assignmentDao) {
		this.assignmentDao = assignmentDao;
	}

	@Override
	public boolean isAssigned(Auditorium auditorium, LocalDateTime dateTime) {
		return assignmentDao.isAssigned(auditorium, dateTime);
	}

	@Override
	public void create(Assignment assignment) {
		assignmentDao.create(assignment);
	}

	@Override
	public void remove(Assignment assignment) {
		assignmentDao.remove(assignment);
	}

	@Override
	public List<Assignment> getAll() {
		return assignmentDao.getAll();
	}

	@Override
	public Assignment getById(Integer id) {
		return assignmentDao.getById(id);
	}

	@Override
	public List<Assignment> getForEvent(Event event) {
		return assignmentDao.getForEvent(event);
	}

}

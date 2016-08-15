package com.epam.spring.movie.service.impl;

import java.util.List;

import com.epam.spring.movie.bean.Auditorium;
import com.epam.spring.movie.dao.AuditoriumDao;
import com.epam.spring.movie.service.AuditoriumService;

public class AuditoriumServiceImpl implements AuditoriumService {

	private AuditoriumDao auditoriumDao;
	
	public void setAuditoriumDao(AuditoriumDao auditoriumDao) {
		this.auditoriumDao = auditoriumDao;
	}

	@Override
	public void create(Auditorium auditorium) {
		auditoriumDao.create(auditorium);
		
	}

	@Override
	public void remove(Auditorium auditorium) {
		auditoriumDao.remove(auditorium);
	}

	@Override
	public List<Auditorium> getAll() {
		return auditoriumDao.getAll();
	}
		
	@Override
	public Auditorium getById(Integer id) {
		return auditoriumDao.getById(id);
	}	

	@Override
	public List<Auditorium> getListByName(String name) {
		return auditoriumDao.getListByName(name);
	}

}

package com.epam.spring.movie.service;

import java.util.List;

import com.epam.spring.movie.bean.Auditorium;


public interface AuditoriumService {

	void create(Auditorium auditorium);

	void remove(Auditorium auditorium);

	List<Auditorium> getAll();
		
	Auditorium getById(Integer id);

	List<Auditorium> getListByName(String name);

}

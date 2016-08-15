package com.epam.spring.movie.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.spring.movie.bean.Auditorium;
import com.epam.spring.movie.bean.Event;
import com.epam.spring.movie.service.AuditoriumService;
import com.epam.spring.movie.service.EventService;

@Controller
public class HomeController {
	private static int EVENTS_PER_PAGE = 12;

	@Autowired
	private EventService eventService;
	
	@Autowired 
	private AuditoriumService auditoriumService;

	@RequestMapping(value = { "/", "/home" })
	public ModelAndView getEventsAndAuditoriums(HttpServletRequest request) {
		
		int eventsCount = eventService.getCount();
		
		//ceil  int pages count
		int pages = (eventsCount - 1) / EVENTS_PER_PAGE + 1;
		
		String pageParam = request.getParameter("page");
		int page = 1;
		if(pageParam != null ) 
				page = Integer.parseInt(pageParam);

		ModelAndView mav = new ModelAndView("home");
		mav.addObject("pages", pages);
		mav.addObject("page", page);
		
		List<Event> events = eventService.getEventsForPage(page, EVENTS_PER_PAGE);
		mav.addObject("events", events);
		
		List<Auditorium> auditoriums = auditoriumService.getAll();
		mav.addObject("auditoriums", auditoriums);
		
		return mav;
	}

	
}

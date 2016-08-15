package com.epam.spring.movie.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.spring.movie.bean.Assignment;
import com.epam.spring.movie.bean.Event;
import com.epam.spring.movie.service.AssignmentService;
import com.epam.spring.movie.service.EventService;

@Controller
public class EventController {
	@Autowired
	private EventService eventService;
	
	@Autowired
	private AssignmentService assignmentService; 
	
	@RequestMapping(value = "/movies/{name}")
	public ModelAndView getEventByName(@PathVariable String name) {
		
		Event event = eventService.getByName(name);
		List<Assignment> assignments = assignmentService.getForEvent(event);
		
		ModelAndView mav = new ModelAndView("event");
		mav.addObject("event", event);
		
		mav.addObject("assignments", assignments);
		
		return mav;
	}
}

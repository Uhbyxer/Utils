package com.epam.spring.movie.mvc;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.epam.spring.movie.bean.Event;
import com.epam.spring.movie.bean.Ticket;
import com.epam.spring.movie.bean.User;
import com.epam.spring.movie.service.EventService;
import com.epam.spring.movie.service.TicketService;
import com.epam.spring.movie.service.UserService;

@Controller
@RequestMapping(value = "/admin-tickets-pdf", headers="Accept=application/pdf")
public class TicketPdfController {
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EventService eventService;
	
	@RequestMapping
    public ModelAndView getTicketsForUserPdf(@RequestParam("user") int userId) {
 		
		User user = userService.getById(userId);
        List<Ticket> tickets = ticketService.getTicketsForUser(user);
        
		ModelAndView mav = new ModelAndView("ticketsForUserPdfView");
		mav.addObject("tickets", tickets);
		mav.addObject("user", user);        

		return mav;
		
    }
	
	@RequestMapping(value = "/{eventName}")
	public ModelAndView getTicketsForEventPdf(@PathVariable String eventName,
											  @RequestParam("time") 
											  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateTime) {
		
		Event event = eventService.getByName(eventName);
		List<Ticket> tickets = ticketService.getTicketsForEvent(event, dateTime);
		
		ModelAndView mav = new ModelAndView("ticketsForEventPdfView");
		mav.addObject("tickets", tickets);
		mav.addObject("event", event);
		mav.addObject("dateTime", dateTime);

		return mav;
	}
	
	
}

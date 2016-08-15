package com.epam.spring.movie.mvc;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.epam.spring.movie.bean.Auditorium;
import com.epam.spring.movie.bean.Ticket;
import com.epam.spring.movie.bean.User;
import com.epam.spring.movie.service.AuditoriumService;
import com.epam.spring.movie.service.TicketService;
import com.epam.spring.movie.service.UserService;

@Controller
@RequestMapping("/admin-users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired 
	private AuditoriumService auditoriumService;
	
	@Autowired
	private TicketService ticketService;
	
	@RequestMapping
	public ModelAndView getUsers(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin-users");

		List<User> users = userService.getAll();
		mav.addObject("users", users);
		
		List<Auditorium> auditoriums = auditoriumService.getAll();
		mav.addObject("auditoriums", auditoriums);
		
		return mav;
	}

	@RequestMapping(value = "/{id}")
	public ModelAndView getUserById(@PathVariable Integer id) {
		
		User user = userService.getById(id);
		List<Ticket> tickets = ticketService.getTicketsForUser(user);
		ModelAndView mav = new ModelAndView("admin-user");
		mav.addObject("user", user);
		mav.addObject("tickets", tickets);
		
		return mav;
	}
	
}

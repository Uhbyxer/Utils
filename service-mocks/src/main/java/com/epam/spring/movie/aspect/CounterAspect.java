package com.epam.spring.movie.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;

import com.epam.spring.movie.bean.Event;
import com.epam.spring.movie.bean.Ticket;
import com.epam.spring.movie.service.EventCounterService;

//@Aspect
//@Component
public class CounterAspect {
	
//	@Autowired
	private EventCounterService eventCounterService;
	
	@Pointcut("execution(* com.epam.spring.movie.service.EventService.getByName(..))")
	private void eventGetByName() {}
	
	@Pointcut("execution(* com.epam.spring.movie.service.EventService.getPrice(..))")
	private void eventGetPrice() {}	

	@Pointcut("execution(* com.epam.spring.movie.service.TicketService.bookTicket(..))")
	private void eventBookTicket() {}	
	
	
	@AfterReturning(pointcut="eventGetByName()", returning="event")
	public void afterEventGetByName(Object event) {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$ ASPECT GET EVENT BY NAME : " + event);
		eventCounterService.incrementAndGetByNameCount((Event) event);
	}
	
	@After("eventGetPrice()&&args(event,..)")
	public void afterEventGetPrice(Object event) {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$ ASPECT GET PRICE : " + event);
		eventCounterService.incrementAndGetPriceCount((Event) event);
	}
	

	
	
	@After("eventBookTicket()")
	public void afterEventBookTicket(JoinPoint jp) {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$ ASPECT  BOOK TICKET FOR EVENT : " + ((Ticket)jp.getArgs()[0]).getEvent());
		eventCounterService.incrementAndGetBookCount(((Ticket)jp.getArgs()[0]).getEvent());
	}
	
	
	
}

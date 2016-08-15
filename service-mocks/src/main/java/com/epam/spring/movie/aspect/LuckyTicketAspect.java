package com.epam.spring.movie.aspect;

import java.util.Random;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;

import com.epam.spring.movie.bean.Ticket;

import com.epam.spring.movie.service.TicketService;

//@Aspect
//@Component
public class LuckyTicketAspect {
	
	//@Autowired
	private TicketService ticketService;

	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	
	@Pointcut("execution(* com.epam.spring.movie.service.TicketService.bookTicket(..))")
	private void bookTicket() {}
	
	@Around("bookTicket() && args(ticket)")
	public void aroundBookTicket(ProceedingJoinPoint point, Object ticket) throws Throwable {
		
		if(new Random().nextInt(100) > 25){
			
			Ticket luckyTicket = (Ticket) ticket;
	
			if(!ticketService.isBooked(luckyTicket.getDateTime(), luckyTicket.getAuditorium(), luckyTicket.getSeat())) {
				
				luckyTicket.clearPricesAndDiscount();
				ticketService.create(luckyTicket);

				System.err.println("$$$$$$$$$$$$$$$$$$$$$$ ASPECT GET LUCKY : " +  luckyTicket);
				
				return;
				
			}			
		}
		point.proceed();
	}
	
}

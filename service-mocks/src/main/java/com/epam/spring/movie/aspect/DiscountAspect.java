package com.epam.spring.movie.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.spring.movie.bean.DiscountStrategy;
import com.epam.spring.movie.bean.Ticket;
import com.epam.spring.movie.service.DiscountStrategyService;
import com.epam.spring.movie.service.UserDiscountCounterService;

//@Aspect
//@Component
@SuppressWarnings("unused")
public class DiscountAspect {
	
	//@Autowired
	private DiscountStrategyService discountStrategyService;
	
	//@Autowired
	private UserDiscountCounterService userDiscountCounterService; 
	
	@Pointcut("execution(* com.epam.spring.movie.service.DiscountStrategyService.getBestDiscountStrategy(..))")
	private void discountBestDiscountStrategy() {}
	
	@AfterReturning(pointcut = "discountBestDiscountStrategy()&& args(ticket,..)",  returning = "discount")
	public void afterDiscountBestDiscountStrategy(Ticket ticket, Object discount) {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$ ASPECT GET DISCOUNT : " + discount + " for " + ticket.getUser());
		
		if(ticket != null && ticket.getUser() != null && discount != null)
			userDiscountCounterService.incrementAndGetCount(ticket.getUser(), (DiscountStrategy) discount);

	}
	
	
}

package com.epam.spring.movie.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@SuiteClasses({ UserServiceTest.class, EventServiceTest.class, AssignmentServiceTest.class,
				AuditoriumServiceTest.class, DiscountStrategyServiceTest.class, TicketServiceTest.class, 
				EventCounterServiceTest.class, UserDiscountCounterServiceTest.class})
public class ServiceSuite {
	
}

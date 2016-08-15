package com.epam.spring.movie.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.spring.movie.AbstractTestCase;
import com.epam.spring.movie.bean.Event;
import com.epam.spring.movie.bean.EventCounter;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventCounterServiceTest extends AbstractTestCase {
	
	public static int testCounter = 0;
	
	@Autowired
	private EventCounterService eventCounterService;
	
	@Autowired
	private EventService eventService;
	
	
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public void setEventCounterService(EventCounterService eventCounterService) {
		this.eventCounterService = eventCounterService;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("\n*********************************************** EventServiceCounter Tests --->");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("\nTest # " + ++testCounter);
	}
	
	
	@Ignore
	@Test
	public void testGetByNameCounter() {
		EventCounter eventCounter = eventCounterService.getByEvent(eventService.getById(1));
		System.out.println("Testing by name counter for event: " + eventCounter.getEvent().getName());
		
		System.out.println(eventCounter.getByNameCount());
		assertEquals(1, eventCounter.getByNameCount());
	}


	@Ignore
	@Test
	public void testGetPriceCounter() {
		Event event = eventService.getById(1);
		System.out.println("Again a price of event: " + eventService.getPrice(event));
		
		EventCounter eventCounter = eventCounterService.getByEvent(event);
		System.out.println("Testing price counter for event: " + eventCounter.getEvent().getName());
		System.out.println(eventCounter.getPriceCount());
		assertEquals(2, eventCounter.getPriceCount());
		
	}

	
	@Ignore
	@Test
	public void testBookCounter() {
		EventCounter eventCounter = eventCounterService.getByEvent(eventService.getById(1));
		System.out.println("Testing book counter for event: " + eventCounter.getEvent().getName());
		
		System.out.println(eventCounter.getBookCount());
		assertEquals(1, eventCounter.getBookCount());
	}
	
}

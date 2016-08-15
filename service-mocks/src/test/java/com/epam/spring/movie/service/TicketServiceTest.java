package com.epam.spring.movie.service;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.spring.movie.AbstractTestCase;
import com.epam.spring.movie.bean.Ticket;
import com.epam.spring.movie.bean.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TicketServiceTest extends AbstractTestCase {
	public static int testCounter = 0;


	@Autowired
	private AuditoriumService auditoriumService; 
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private TicketService ticketService;

	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("\n*********************************************** Ticket Tests --->");
	}
	
	private Ticket newTicketFirst;

	@Before
	public void setUp() throws Exception {
		System.out.println("\nTest # " + ++testCounter);
		
		newTicketFirst = new Ticket();
		newTicketFirst.setAuditorium(auditoriumService.getById(1));
		newTicketFirst.setEvent(eventService.getById(1));
		newTicketFirst.setSeat(10);
		newTicketFirst.setUser(userService.getById(1));
		newTicketFirst.setDateTime(LocalDateTime.of(2016, 03, 01, 21, 45));
	}

	@Test
	public void testGetTicketsForEvent() {
		System.out.println("Getting tickets for event: " + newTicketFirst.getEvent());
		System.out.println("For time: " + newTicketFirst.getDateTime());
		List<Ticket> list = ticketService.getTicketsForEvent(newTicketFirst.getEvent(), newTicketFirst.getDateTime());
		list.forEach(System.out::println);
		assertTrue(list.size() > 0);
	}

	
	@Ignore
	@Test
	public void testToBookTicket() {
		
		int before = ticketService.getAll().size();

		System.out.println("Try to book ticket: " + newTicketFirst);
	
		ticketService.bookTicket(newTicketFirst);
		System.out.println("Ticket price: " + newTicketFirst.getBillDetails());

		assertEquals(before + 1, ticketService.getAll().size());

	}

	@Test
	public void testGetAll() {
		System.out.println("Get all booked tickets:");
		List<Ticket> list = ticketService.getAll();
		list.forEach(System.out::println);
		assertTrue(list.size() > 0);
	}

	@Test
	public void testNewTicket() {
		System.out.println("Create new ticket:");
		List<Ticket> list = ticketService.getAll();
		Ticket ticket = list.get(0);
		ticket.setHour(8);
		System.out.println(ticket);
		ticketService.create(ticket);

		// ticketService.getAll().forEach(System.out::println);
		assertEquals(list.size() + 1, ticketService.getAll().size());
	}

	@Test
	public void testGetCountOfTicketsForUser() {
		System.out.println("Count of tickets bought by User: " + newTicketFirst.getUser());
		long count = ticketService.getCountOfTicketsForUser(newTicketFirst.getUser());
		System.out.println(count);
		assertTrue(count > 0);
	}

	@Test
	public void testIsBooked() {
		System.out.println("Get status of ticket: " + newTicketFirst);

		boolean booked = ticketService.isBooked(newTicketFirst.getDateTime(), newTicketFirst.getAuditorium(),
				newTicketFirst.getSeat());
		if (booked) {
			System.out.println("BOOKED ALREADY !!!");

		} else {
			System.out.println("IS FREE ");
		}

		assertFalse(booked);
	}

	@Test
	public void testGetTicketsForUser() {
		User user = userService.getById(0);
		List<Ticket> list = ticketService.getTicketsForUser(user);
		System.out.println("All bought tickets for user: " + user.getName());
		System.out.println("Tickets:");
		list.forEach(System.out::println);
		assertTrue(list.size() > 0);
	}

	@Test
	public void testCalculatePriceFirstCase() {
		User userFirst = newTicketFirst.getUser();
		System.out.println("Calculating price for Ticket");
		System.out.println("User: " + userFirst);
		System.out.println("Event: " + newTicketFirst.getEvent());
		System.out.println("Booking time:" + newTicketFirst.getDateTime());
		long count = ticketService.getCountOfTicketsForUser(userFirst);
		System.out.println("Number tickets user bought before:" + count);
		ticketService.calculatePrice(newTicketFirst);
		System.out.println("\nBill details:");
		System.out.println(newTicketFirst.getBillDetails());
		assertTrue(newTicketFirst.getPrice() > 0);

	}

	@Test
	public void testCalculatePriceSecondCase()  {
		User user = userService.getById(0);
		newTicketFirst.setUser(user);
		System.out.println("Calculating price for Ticket");
		System.out.println("User: " + user);
		System.out.println("Event: " + newTicketFirst.getEvent());
		System.out.println("Booking time:" + newTicketFirst.getDateTime());
		long count = ticketService.getCountOfTicketsForUser(user);
		System.out.println("Number tickets user bought before:" + count);
		ticketService.calculatePrice(newTicketFirst);
		System.out.println("\nBill details:");
		System.out.println(newTicketFirst.getBillDetails());
		assertTrue(newTicketFirst.getPrice() > 0);
	}

	@Test
	public void testCalculatePriceNotRegisteredUser()  {
		
		newTicketFirst.setUser(null);

		System.out.println("Calculating price of Ticket for not registered User");

		System.out.println("Event: " + newTicketFirst.getEvent());
		System.out.println("Booking time:" + newTicketFirst.getDateTime());

		ticketService.calculatePrice(newTicketFirst);
		System.out.println("\nBill details:");
		System.out.println(newTicketFirst.getBillDetails());
		assertTrue(newTicketFirst.getPrice() > 0);
	}
}

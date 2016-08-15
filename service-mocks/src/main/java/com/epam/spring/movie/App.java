package com.epam.spring.movie;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
	public static void main(String[] args) {
		
		@SuppressWarnings({ "resource", "unused" })
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		//App app = (App) ctx.getBean("app");
		
	}
}

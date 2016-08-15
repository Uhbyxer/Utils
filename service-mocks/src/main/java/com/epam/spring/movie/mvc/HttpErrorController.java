package com.epam.spring.movie.mvc;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HttpErrorController {
	
	@RequestMapping("/error")
	public ModelAndView handelError(HttpServletRequest request) {
		// Lets get the status code and uri from the request
		final Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}
		
		String message = MessageFormat.format("{0} returned for {1}", statusCode, requestUri);

		ModelAndView mav = new ModelAndView("http-error");
		mav.addObject("errorMessage", message);
		
		return mav;
	}
}

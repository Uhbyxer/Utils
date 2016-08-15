package com.utils.responsys.controller;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.utils.responsys.domain.Token;

@Controller
@RequestMapping("responsys")
public class LoginController {
	
	@RequestMapping(value = "auth/token", method = RequestMethod.POST)
	public ResponseEntity<Token> getToken(HttpServletRequest request) {
		Token token = new Token();
		token.setAuthToken("mxpbmd1czpnaGY3MzJmaDc0Z3E=");
		token.setEndPoint(request.getRequestURI());
		token.setIssuedAt(LocalDateTime.now().toString());
		return new ResponseEntity<Token>(token, HttpStatus.OK);
	}
}
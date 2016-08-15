package com.epam.spring.movie.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


public class User extends NamedBean {

	private String email;
	
	
	private LocalDate birth;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getBirth() {
		return birth;
	}
	
	@JsonProperty("birth")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
	public void setBirthDate(java.util.Date date) {
		birth = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
	}
	
	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return name + " <" + email + ">" 
					+ (birth == null ? "" : " (birth: " + birth + ")");
	}
	
}

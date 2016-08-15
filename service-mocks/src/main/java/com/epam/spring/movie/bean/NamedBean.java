package com.epam.spring.movie.bean;

public abstract class NamedBean extends BaseBean {
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

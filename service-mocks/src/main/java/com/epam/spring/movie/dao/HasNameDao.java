package com.epam.spring.movie.dao;

import java.util.List;

import com.epam.spring.movie.bean.BaseBean;

public interface HasNameDao <T extends BaseBean> {
	public List<T> getListByName(String name);
}

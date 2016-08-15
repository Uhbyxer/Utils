package com.epam.spring.movie.dao.stub;

import java.util.List;
import java.util.stream.Collectors;


import com.epam.spring.movie.bean.NamedBean;
import com.epam.spring.movie.dao.HasNameDao;

public class BaseNamedDaoStub <T extends NamedBean> extends BaseDaoStub<T> implements HasNameDao<T> {

	@Override
	public List<T> getListByName(String name) {
		return  holder.entrySet()
				.stream()
				.filter(p -> p.getValue().getName().compareToIgnoreCase(name) == 0)
				.map(p -> p.getValue())
				.collect(Collectors.toList());
	}

}

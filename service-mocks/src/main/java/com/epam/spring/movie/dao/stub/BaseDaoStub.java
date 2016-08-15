package com.epam.spring.movie.dao.stub;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.epam.spring.movie.bean.BaseBean;
import com.epam.spring.movie.dao.BaseDao;


public class BaseDaoStub <T extends BaseBean> implements BaseDao<T> {
	
	protected Map<Integer, T> holder;
	
	public void setHolder(Map<Integer, T> holder) {
		this.holder = holder;
	}
	
	@Override
	public void create(T  bean) {
		if(bean.getId() == null) {
			bean.setId(getMaxid() + 1);
		}
		holder.putIfAbsent(bean.getId(), bean);	
	}

	@Override
	public void remove(T t) {
		holder.remove(t.getId());
		
	}

	@Override
	public List<T> getAll() {
		return  holder.entrySet()
				.stream()
				.map(p -> p.getValue())
				.collect(Collectors.toList());
	}

	@Override
	public T getById(Integer id) {
		return holder.get(id);
	}
	
	public int getMaxid() {
		return holder.keySet()
				.stream()
				.max(Integer::compareTo)
				.orElse(-1);
	}
}

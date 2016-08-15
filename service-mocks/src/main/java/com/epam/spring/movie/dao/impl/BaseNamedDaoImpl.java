package com.epam.spring.movie.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import com.epam.spring.movie.bean.NamedBean;
import com.epam.spring.movie.dao.HasNameDao;

public abstract class BaseNamedDaoImpl <T extends NamedBean> extends BaseDaoImpl<T> implements HasNameDao<T>{

	protected final String getByNameSql;
	
	public BaseNamedDaoImpl(String tableName) {
		super(tableName);
		
		getByNameSql = "select * from " + tableName + " where lower(name) = lower(?)";
	}

	@Override
	public List<T> getListByName(String name) {
		return  jdbcTemplate.query(getByNameSql, new Object[] { name }, new RowMapper<T>() {
			
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {

				return getBeanFromResultSet(rs);
				
			}

		});

	}


}

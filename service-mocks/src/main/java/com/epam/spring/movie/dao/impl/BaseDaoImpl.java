package com.epam.spring.movie.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.epam.spring.movie.bean.BaseBean;
import com.epam.spring.movie.dao.BaseDao;

public abstract class BaseDaoImpl <T extends BaseBean> implements BaseDao<T> {

	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	private final String tableName;
	private final String deleteSql;
	private final String getAllSql;
	private final String getByIdSql;
	
	
	public String getTableName() {
		return tableName;
	}

	@Override
	public void remove(T t) {
		jdbcTemplate.update(
				deleteSql,
				t.getId()
		);		
	}

	@Override
	public List<T> getAll() {
		
		return  jdbcTemplate.query(getAllSql, new RowMapper<T>() {
			
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {

				return getBeanFromResultSet(rs);
				
			}

		});
	}

	@Override
	public T getById(Integer id) {
		try {

			return jdbcTemplate.queryForObject(getByIdSql, new Object[] { id },

			new RowMapper<T>() {
				public T mapRow(ResultSet rs, int rowNum) throws SQLException {

					return getBeanFromResultSet(rs);

				}
			});

		} catch (Exception e) {

			// no rows
			return null;
		}
	}
	
	public BaseDaoImpl(String tableName) {
		this.tableName = tableName;
		
		deleteSql = "delete from " + tableName + " where id = ?";
		getAllSql = "select * from " + tableName; 
		getByIdSql = "select * from " + tableName + " where id = ?";
	}
	
	public abstract void create(T t);
	
	protected abstract T getBeanFromResultSet(ResultSet rs) throws SQLException;
	
}

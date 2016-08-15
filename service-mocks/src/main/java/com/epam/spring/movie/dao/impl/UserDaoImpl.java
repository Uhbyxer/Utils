package com.epam.spring.movie.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import org.springframework.jdbc.core.RowMapper;
import com.epam.spring.movie.bean.User;
import com.epam.spring.movie.dao.UserDao;

public class UserDaoImpl extends BaseNamedDaoImpl<User> implements UserDao {
	
	private static final String GET_BY_EMAIL = "select * from user where lower(email) = lower(?)"; 
	private static final String INSERT_RECORD = "insert into user (name, email, birth) VALUES (?,?,?)";
	
	
	@Override
	protected User getBeanFromResultSet(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setEmail(rs.getString("email"));
		user.setName(rs.getString("name"));
		
		if(rs.getObject("birth") != null) {
			try {
				user.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(rs.getString("birth"))));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}
	

	@Override
	public void create(User user) {
		jdbcTemplate.update(
				INSERT_RECORD,
				user.getName(),
				user.getEmail(),
				
				user.getBirth() == null ? null : 
					java.util.Date.from(user.getBirth().atStartOfDay(ZoneId.systemDefault()).toInstant())
		);
	}



	@Override
	public User getUserByEmail(String email) {

		try {

			return jdbcTemplate.queryForObject(GET_BY_EMAIL, new Object[] { email },

			new RowMapper<User>() {
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {

					return getBeanFromResultSet(rs);

				}
			});

		} catch (Exception e) {
			
			//no rows
			return null;
		}
	}

	public UserDaoImpl() {
		super("user");
	}

}

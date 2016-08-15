package com.epam.spring.movie.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.epam.spring.movie.bean.DiscountStrategy;
import com.epam.spring.movie.bean.User;
import com.epam.spring.movie.bean.UserDiscountCounter;
import com.epam.spring.movie.dao.DiscountStrategyDao;
import com.epam.spring.movie.dao.UserDao;
import com.epam.spring.movie.dao.UserDiscountCounterDao;

public class UserDiscountCounterDaoImpl extends BaseDaoImpl<UserDiscountCounter> implements UserDiscountCounterDao {
	
	private static final String INSERT_RECORD = "insert into discount_counter (user_id, discount_id, count) VALUES (?,?,?)";
	
	private static final String UPDATE_RECORD = 
			"update discount_counter set user_id = ?, discount_id = ?, count = ? where id = ?";
	
	private static final String GET_BY_USER_AND_DISCOUNT = "select * from discount_counter where user_id = ? and discount_id = ?"; 
	
	private static final String GET_SUM_COUNT_BY_DISCOUNT = "select sum(count) from discount_counter where discount_id = ?";

	private UserDao userDao;
	
	private DiscountStrategyDao discountStrategyDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setDiscountStrategyDao(DiscountStrategyDao discountStrategyDao) {
		this.discountStrategyDao = discountStrategyDao;
	}

	@Override
	protected UserDiscountCounter getBeanFromResultSet(ResultSet rs) throws SQLException {
		UserDiscountCounter userDiscountCounter = new UserDiscountCounter();
		userDiscountCounter.setId(rs.getInt("id"));
		userDiscountCounter.setUser(userDao.getById(rs.getInt("user_id")));
		userDiscountCounter.setDiscountStrategy(discountStrategyDao.getById(rs.getInt("discount_id")));
		userDiscountCounter.setCount(rs.getInt("count"));
		
		return userDiscountCounter;
	}
	
	@Override
	public UserDiscountCounter getByUserAndDiscount(User user, DiscountStrategy discountStrategy) {
		try {

			return jdbcTemplate.queryForObject(GET_BY_USER_AND_DISCOUNT, new Object[] { user.getId(), discountStrategy.getId()},

			new RowMapper<UserDiscountCounter>() {
				public UserDiscountCounter mapRow(ResultSet rs, int rowNum) throws SQLException {

					return getBeanFromResultSet(rs);

				}
			});

		} catch (Exception e) {
			
			// no rows
			return null;
		}
	}
	
	public UserDiscountCounter getByUserAndDiscountOrCreate(User user, DiscountStrategy discountStrategy) {
		
		UserDiscountCounter userDiscountCounter = getByUserAndDiscount(user, discountStrategy);
		if(userDiscountCounter == null) {
			userDiscountCounter = new UserDiscountCounter();
			userDiscountCounter.setUser(user);
			userDiscountCounter.setDiscountStrategy(discountStrategy);
			create(userDiscountCounter);
		}
		return userDiscountCounter;
	}	

	@Override
	public int incrementAndGetCount(User user, DiscountStrategy discountStrategy) {
		UserDiscountCounter userDiscountCounter = getByUserAndDiscountOrCreate(user, discountStrategy);
		userDiscountCounter.setCount(userDiscountCounter.getCount() + 1);
		update(userDiscountCounter);
		return userDiscountCounter.getCount();
	}

	@Override
	public int getTotalCountByDiscount(DiscountStrategy discountStrategy) {
		return jdbcTemplate.queryForObject(
				GET_SUM_COUNT_BY_DISCOUNT, Integer.class, 
				discountStrategy.getId());
	}

	@Override
	public void create(UserDiscountCounter userDiscountCounter) {
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				connection -> {
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RECORD,
							Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setInt(1, userDiscountCounter.getUser().getId());
					preparedStatement.setInt(2, userDiscountCounter.getDiscountStrategy().getId());
					preparedStatement.setInt(3, userDiscountCounter.getCount());
					return preparedStatement;

				}, holder);

		userDiscountCounter.setId(holder.getKey().intValue());
	}

	public UserDiscountCounterDaoImpl() {
		super("discount_counter");
	}

	@Override
	public void update(UserDiscountCounter userDiscountCounter) {
		jdbcTemplate.update(
				UPDATE_RECORD,
				userDiscountCounter.getUser().getId(),
				userDiscountCounter.getDiscountStrategy().getId(),
				userDiscountCounter.getCount(),
				userDiscountCounter.getId()
		);
		
	}


}

package com.epam.spring.movie.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.epam.spring.movie.bean.Assignment;
import com.epam.spring.movie.bean.Auditorium;
import com.epam.spring.movie.bean.Event;
import com.epam.spring.movie.dao.AssignmentDao;
import com.epam.spring.movie.dao.AuditoriumDao;
import com.epam.spring.movie.dao.EventDao;

public class AssignmentDaoImpl extends BaseDaoImpl<Assignment> implements AssignmentDao {
	
	private static final String INSERT_RECORD = "insert into assignment (event_id, auditorium_id, assignment_datetime) VALUES (?,?,?)";
	private static final String GET_BY_AUDITORIUM_AND_TIME = "select count(*) from assignment where auditorium_id = ? and assignment_datetime = ?"; 
	private static final String GET_BY_EVENT = "select * from assignment where event_id = ?"; 

	private EventDao eventDao;
	private AuditoriumDao auditoriumDao;
	
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	public void setAuditoriumDao(AuditoriumDao auditoriumDao) {
		this.auditoriumDao = auditoriumDao;
	}
	
	public AssignmentDaoImpl() {
		super("assignment");
	}


	@Override
	public void create(Assignment assignment) {
		jdbcTemplate.update(
				INSERT_RECORD,
				assignment.getEvent().getId(),
				assignment.getAuditorium().getId(),
				java.util.Date.from(assignment.getDateTime().atZone(ZoneId.systemDefault()).toInstant())
		);
		
	}
	
	@Override
	protected Assignment getBeanFromResultSet(ResultSet rs) throws SQLException {
		Assignment assignment = new Assignment();
		assignment.setId(rs.getInt("id"));
		assignment.setEvent(eventDao.getById(rs.getInt("event_id"))); 
		assignment.setAuditorium(auditoriumDao.getById(rs.getInt("auditorium_id")));
		
		if(rs.getObject("assignment_datetime") != null) {
			try {
				assignment.setDate(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(String.valueOf(rs.getString("assignment_datetime"))));
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
			
		return assignment;
	}
	

	@Override
	public boolean isAssigned(Auditorium auditorium, LocalDateTime dateTime) {

			return jdbcTemplate.queryForObject(
					GET_BY_AUDITORIUM_AND_TIME,Integer.class, 
					auditorium.getId(),
					java.util.Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant())) > 0;

	}

	@Override
	public List<Assignment> getForEvent(Event event) {
		return  jdbcTemplate.query(GET_BY_EVENT, new Object[] {event.getId()}, new RowMapper<Assignment>() {
			
			public Assignment mapRow(ResultSet rs, int rowNum) throws SQLException {

				return getBeanFromResultSet(rs);
				
			}

		});
	}

}

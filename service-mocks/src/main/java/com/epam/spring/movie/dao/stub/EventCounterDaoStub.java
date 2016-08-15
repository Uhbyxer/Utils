package com.epam.spring.movie.dao.stub;

import java.util.HashMap;
import com.epam.spring.movie.bean.Event;
import com.epam.spring.movie.bean.EventCounter;
import com.epam.spring.movie.dao.EventCounterDao;

public class EventCounterDaoStub extends BaseDaoStub<EventCounter> implements EventCounterDao {
	
	@Override
	public EventCounter getByEvent(Event event) {
		if(event == null) return null;
		
		EventCounter res = holder.entrySet()
				.stream()
				.filter(p -> event.getId().equals(p.getValue().getEvent().getId()))
				//.filter(p -> event.equals(p.getValue().getEvent()))
				.map(p -> p.getValue())
				.findFirst()
				.orElse(null);
		
		
		return res;
	}
	
	public EventCounter getByEventOrCreate(Event event) {
		
		EventCounter eventCounter = getByEvent(event);
		if(eventCounter == null) {
			eventCounter = new EventCounter();
			eventCounter.setEvent(event);
			create(eventCounter);
		}
		return eventCounter;
	}

	@Override
	public int incrementAndGetByNameCount(Event event) {
		EventCounter eventCounter = getByEventOrCreate(event);
		eventCounter.setByNameCount(eventCounter.getByNameCount() + 1);
		return eventCounter.getByNameCount();
	}

	@Override
	public int incrementAndGetPriceCount(Event event) {
		EventCounter eventCounter = getByEventOrCreate(event);
		eventCounter.setPriceCount(eventCounter.getPriceCount() + 1);
		return eventCounter.getPriceCount();
	}

	@Override
	public int incrementAndGetBookCount(Event event) {
		EventCounter eventCounter = getByEventOrCreate(event);
		eventCounter.setBookCount(eventCounter.getBookCount() + 1);
		return eventCounter.getBookCount();
	}
	
	public EventCounterDaoStub() {
		holder = new HashMap<>();
	}

	@Override
	public void update(EventCounter eventCounter) {
		// TODO Auto-generated method stub
	}
	
}

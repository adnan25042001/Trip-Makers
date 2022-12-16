package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AdminException;
import com.masai.exception.BusException;
import com.masai.model.Bus;
import com.masai.model.CurrentUserSession;
import com.masai.model.Route;
import com.masai.model.UserType;
import com.masai.repository.BusDao;
import com.masai.repository.UserSessionDao;

@Service
public class BusServiceImpl implements BusService {

	@Autowired
	private UserSessionDao usdao;

	@Autowired
	private BusDao bdao;

	@Override
	public Bus addBus(Bus bus, String authKey) throws BusException {

		Optional<CurrentUserSession> opt = usdao.findByAuthKey(authKey);

		if (opt.isEmpty())
			throw new AdminException("Invalid authkey : " + authKey);

		CurrentUserSession cus = opt.get();

		if (cus.getUserType().equals(UserType.CUSTOMER))
			throw new AdminException("Invalid authkey : " + authKey);

		bdao.save(bus);

		return bus;

	}

	@Override
	public Bus deleteBus(String busNo, String authKey) throws BusException {

		Optional<CurrentUserSession> opt = usdao.findByAuthKey(authKey);

		if (opt.isEmpty())
			throw new AdminException("Invalid authkey : " + authKey);

		CurrentUserSession cus = opt.get();

		if (cus.getUserType().equals(UserType.CUSTOMER))
			throw new AdminException("Invalid authkey : " + authKey);
		
		Optional<Bus> opt1 = bdao.findByBusNo(busNo);
		
		if(opt1.isEmpty()) throw new BusException("Bus not found with busNo. : " + busNo);

		bdao.delete(opt1.get());

		return opt1.get();

	}

	@Override
	public Bus getBusByBusNo(String busNo, String authKey) throws BusException {
		
		Optional<CurrentUserSession> opt = usdao.findByAuthKey(authKey);
		
		if(opt.isEmpty()) throw new RuntimeException("Invalid authKey : " + authKey);
		
		Optional<Bus> opt1 = bdao.findByBusNo(busNo);
		
		if(opt1.isEmpty()) throw new BusException("Bus not found with busNo : " + busNo);
		
		return opt1.get();
		
	}

	@Override
	public List<Bus> getAllBus(String authKey) throws BusException {
		
		List<Bus> buses = bdao.findAll();
		
		if(buses.size() == 0) throw new BusException("Bus not found!");
		
		return buses;
		
	}

	@Override
	public Route addRoute(String busNo, Integer routeId, String authKey) {
		return null;
	}

}

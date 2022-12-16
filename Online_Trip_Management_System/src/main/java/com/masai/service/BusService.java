package com.masai.service;

import java.util.List;

import com.masai.exception.BusException;
import com.masai.model.Bus;
import com.masai.model.Route;

public interface BusService {
	
	public Bus addBus(Bus bus, String authKey) throws BusException;
	
	public Bus deleteBus(String busNo, String authKey) throws BusException;
	
	public Bus getBusByBusNo(String busNo, String authKey) throws BusException;
	
	public List<Bus> getAllBus(String authKey) throws BusException;
	
	public Route addRoute(String busNo, Integer routeId, String authKey);

}

package com.masai.service;

import java.util.List;

import com.masai.model.Hotel;

public interface HotelService {
	
	public Hotel addHotel(Hotel hotel,String key);
	
	public String deleteHotel(String name,String key);
	
	public Hotel updateHotel(Hotel hotel,String key);
	
	public List<Hotel> viewAllHotel();

}

package com.masai.service;

import java.util.List;

import com.masai.model.Hotel;
import com.masai.model.HotelDto;

public interface HotelService {
	
	public Hotel addHotel(Hotel hotel,String key);
	
	public String deleteHotel(Integer id,String key);
	
	public Hotel updateHotel(HotelDto hotel,String key);
	
	public List<Hotel> viewAllHotel();

}

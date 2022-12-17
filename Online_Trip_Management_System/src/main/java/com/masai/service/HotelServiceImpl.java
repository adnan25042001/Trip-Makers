package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AdminException;
import com.masai.exception.HotelException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Hotel;

import com.masai.model.HotelDto;

import com.masai.model.UserType;
import com.masai.repository.HotelDao;
import com.masai.repository.UserSessionDao;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private UserSessionDao uSesDao;

	@Autowired
	private HotelDao hotelDao;

	@Override
	public Hotel addHotel(Hotel hotel, String key) {

		Optional<CurrentUserSession> opt = uSesDao.findByAuthKey(key);
		if (opt.isEmpty())
			throw new AdminException("Invalid Authentication Id of Admin" + key);

		if (opt.get().getUserType().equals(UserType.CUSTOMER))
			throw new AdminException("Invalid authKey : " + key);

		hotelDao.save(hotel);

		return hotel;

	}

	@Override
	public String deleteHotel(Integer hotelId, String key) {

		Optional<CurrentUserSession> opt = uSesDao.findByAuthKey(key);
		if (opt.isEmpty()) {
			throw new HotelException("Invalid Authentication Id of Admin" + key);
		}

		if (opt.get().getUserType().equals(UserType.CUSTOMER))
			throw new AdminException("Invalid authKey : " + key);

		Optional<Hotel> opth = hotelDao.findById(hotelId);

		if (opth.isEmpty())
			throw new HotelException("Hotel not found with Id : " + hotelId);

		hotelDao.delete(opth.get());

		return "Hotel Deleted Successfully";

	}

	@Override
	public Hotel updateHotel(HotelDto hotel, String key) {

		Optional<CurrentUserSession> opt = uSesDao.findByAuthKey(key);

		if (opt.isEmpty())
			throw new HotelException("Invalid Authentication Id of Admin" + key);

		if (opt.get().getUserType().equals(UserType.CUSTOMER))
			throw new AdminException("Invalid authKey : " + key);

		Optional<Hotel> hot = hotelDao.findById(hotel.getId());

		if (hot.isEmpty())
			throw new HotelException("Hotel not found with this Id :" + hotel.getId());

		Hotel hotl = hot.get();

		hotl.setRent(hotel.getRent());
		hotl.setStatus(hotel.getStatus());

		return hotelDao.save(hotl);

	}

	@Override
	public List<Hotel> viewAllHotel() {

		List<Hotel> hotels = hotelDao.findAll();

		if (hotels.size() == 0)
			throw new HotelException("Hotels not found");

		return hotels;

	}

}

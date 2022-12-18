package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AdminException;
import com.masai.exception.RouteException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Route;
import com.masai.model.UserType;
import com.masai.repository.RouteDao;
import com.masai.repository.UserSessionDao;

@Service
public class RouteServiceImpl implements RouteService {

	@Autowired
	private UserSessionDao usdao;

	@Autowired
	private RouteDao rdao;

	@Override
	public Route addRoute(Route route, String authKey) {

		CurrentUserSession cus = usdao.findByAuthKey(authKey)
				.orElseThrow(() -> new AdminException("Invalid authKey : " + authKey));

		if (cus.getUserType().equals(UserType.CUSTOMER))
			throw new AdminException("Invalid authKey : " + authKey);

		return rdao.save(route);

	}

	@Override
	public Route deleteRoute(Integer routeId, String authKey) throws RouteException {

		CurrentUserSession cus = usdao.findByAuthKey(authKey)
				.orElseThrow(() -> new AdminException("Invalid authKey : " + authKey));

		if (cus.getUserType().equals(UserType.CUSTOMER))
			throw new AdminException("Invalid authKey : " + authKey);

		Route route = rdao.findById(routeId)
				.orElseThrow(() -> new RouteException("Route not found with id : " + routeId));

		rdao.delete(route);

		return route;

	}

	@Override
	public Route getRouteByRouteId(Integer routeId, String authKey) throws RouteException {

		usdao.findByAuthKey(authKey).orElseThrow(() -> new AdminException("Invalid authKey : " + authKey));

		return rdao.findById(routeId).orElseThrow(() -> new RouteException("Route not found with id : " + routeId));

	}

	@Override
	public List<Route> getAllRoute(String authKey) throws RouteException {

		usdao.findByAuthKey(authKey).orElseThrow(() -> new AdminException("Invalid authKey : " + authKey));

		List<Route> routes = rdao.findAll();

		if (routes.size() == 0)
			throw new RouteException("Route not found!");

		return routes;

	}

	@Override
	public List<Route> getRouteByPickupPoint(String pickup, String authKey) throws RouteException {

		usdao.findByAuthKey(authKey).orElseThrow(() -> new AdminException("Invalid authKey : " + authKey));

		List<Route> routes = rdao.findByPickupPoint(pickup);

		if (routes.size() == 0)
			throw new RouteException("Route not found!");

		return routes;

	}

}

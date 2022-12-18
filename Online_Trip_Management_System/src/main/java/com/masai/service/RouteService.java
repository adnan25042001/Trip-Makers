package com.masai.service;

import java.util.List;

import com.masai.exception.RouteException;
import com.masai.model.Route;

public interface RouteService {

	public Route addRoute(Route route, String authKey);

	public Route deleteRoute(Integer routeId, String authKey) throws RouteException;

	public Route getRouteByRouteId(Integer routeId, String authKey) throws RouteException;

	public List<Route> getAllRoute(String authKey) throws RouteException;

	public List<Route> getRouteByPickupPoint(String pickup, String authKey) throws RouteException;

}

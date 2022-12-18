package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Route;
import com.masai.service.RouteService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/tripmakers")
public class RouteController {

	@Autowired
	private RouteService rService;

	@PostMapping("/addroute/{authKey}")
	public ResponseEntity<Route> addRouteHandler(@Valid @RequestBody Route route, @PathVariable String authKey) {

		return new ResponseEntity<Route>(rService.addRoute(route, authKey), HttpStatus.CREATED);

	}

	@DeleteMapping("/removeroute/{authKey}?routeid")
	public ResponseEntity<Route> addRouteHandler(@RequestParam("routeid") Integer routeId,
			@PathVariable String authKey) {

		return new ResponseEntity<Route>(rService.deleteRoute(routeId, authKey), HttpStatus.OK);

	}

	@GetMapping("/getrouteById/{authKey}?routeid")
	public ResponseEntity<Route> getRouteHandler(@RequestParam("routeid") Integer routeId,
			@PathVariable String authKey) {

		return new ResponseEntity<Route>(rService.getRouteByRouteId(routeId, authKey), HttpStatus.OK);

	}

	@GetMapping("/routebypickuppoint/{authKey}?pickuppoint")
	public ResponseEntity<List<Route>> getRouteByPickupPointHandler(@RequestParam("pickuppoint") String pickup,
			@PathVariable String authKey) {

		return new ResponseEntity<List<Route>>(rService.getRouteByPickupPoint(pickup, authKey), HttpStatus.OK);

	}

	@GetMapping("/allroutes/{authKey}")
	public ResponseEntity<List<Route>> getAllRoutesHandler(@PathVariable String authKey) {

		return new ResponseEntity<List<Route>>(rService.getAllRoute(authKey), HttpStatus.OK);

	}

}

package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Bus;
import com.masai.model.Route;
import com.masai.service.BusService;

@RestController
@RequestMapping("/tripmakers")
public class BusController {

	private BusService bService;

	@PostMapping("/addbus/{authKey}")
	public ResponseEntity<Bus> addBusHandler(@Valid @RequestBody Bus bus, @PathVariable("authKey") String authKey) {

		return new ResponseEntity<Bus>(bService.addBus(bus, authKey), HttpStatus.CREATED);

	}

	@DeleteMapping("/removebus/{authKey}?busno")
	public ResponseEntity<Bus> deleteBusHandler(@RequestParam("busno") String busNo,
			@PathVariable("authKey") String authKey) {

		return new ResponseEntity<Bus>(bService.deleteBus(busNo, authKey), HttpStatus.OK);

	}

	@GetMapping("/getbus/{authKey}?busno")
	public ResponseEntity<Bus> getBusByBusNoHandler(@RequestParam("busno") String busNo,
			@PathVariable("authKey") String authKey) {

		return new ResponseEntity<Bus>(bService.getBusByBusNo(busNo, authKey), HttpStatus.OK);

	}

	@GetMapping("/getallbus/{authKey}")
	public ResponseEntity<List<Bus>> getBusByBusNoHandler(@PathVariable("authKey") String authKey) {

		return new ResponseEntity<List<Bus>>(bService.getAllBus(authKey), HttpStatus.OK);

	}

	@GetMapping("/addroutetobus/{authKey}?busno?routeid")
	public ResponseEntity<Route> addRouteToBusNoHandler(@RequestParam("busno") String busNo,
			@RequestParam("routeid") Integer routeId, @PathVariable("authKey") String authKey) {

		return new ResponseEntity<Route>(bService.addRoute(busNo, routeId, authKey), HttpStatus.CREATED);

	}

}

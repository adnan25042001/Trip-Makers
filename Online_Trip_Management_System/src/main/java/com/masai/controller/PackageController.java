package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.PackageException;
import com.masai.model.Package;
import com.masai.model.PackageDto;
import com.masai.service.BookingService;
import com.masai.service.PackageService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/package")
public class PackageController {

	@Autowired
	public PackageService pservice;

	@Autowired
	public BookingService bservice;

	@PostMapping("/create/{key}")
	public ResponseEntity<Package> createpackage(@RequestBody Package p, @PathVariable("key") String key)
			throws PackageException {
		Package newpackage = pservice.createPackage(p, key);
		return new ResponseEntity<Package>(newpackage, HttpStatus.ACCEPTED);
	}

	@PostMapping("/delete/{id}/{key}")
	public ResponseEntity<Package> deletePackageById(@PathVariable("id") Integer id, @PathVariable("key") String key)
			throws PackageException {
		Package p = pservice.deletePackagebyId(id, key);
		return new ResponseEntity<Package>(p, HttpStatus.OK);

	}

	@PostMapping("/view/{id}/")
	public ResponseEntity<Package> viewPackageById(@PathVariable("id") Integer id) throws PackageException {
		Package p = pservice.viewPackageById(id);
		return new ResponseEntity<Package>(p, HttpStatus.OK);

	}

	@GetMapping("/viewAll")
	public ResponseEntity<List<Package>> viewAllPackages() throws PackageException {
		List<Package> list = pservice.viewAllPackage();
		return new ResponseEntity<List<Package>>(list, HttpStatus.OK);
	}

	@GetMapping("/book/{id}/{key}")
	public ResponseEntity<PackageDto> bookpackage(@PathVariable("id") Integer id, @PathVariable("key") String key)
			throws PackageException {
		PackageDto dto = bservice.bookPackage(id, key);
		return new ResponseEntity<PackageDto>(dto, HttpStatus.ACCEPTED);
	}

	@GetMapping("/remove/{id}/{key}")
	public ResponseEntity<String> removePackage(@PathVariable("id") Integer id, @PathVariable("key") String key)
			throws PackageException {

		String str = bservice.cancelPackage(id, key);
		return new ResponseEntity<String>(str, HttpStatus.OK);

	}

}

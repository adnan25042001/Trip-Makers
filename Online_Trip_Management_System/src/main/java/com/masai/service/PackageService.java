package com.masai.service;

import java.util.List;

import com.masai.model.Package;
import com.masai.exception.PackageException;

public interface PackageService {

	public Package addPackage(Package pack) throws PackageException;
	public Package deletePackagebyId(Integer packageId) throws PackageException;
	public Package viewPackageById(Integer packageId)throws PackageException;
	public List<Package> viewAllPackage() throws PackageException;
	
}

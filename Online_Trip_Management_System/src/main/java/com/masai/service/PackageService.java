package com.masai.service;

import java.util.List;
import com.masai.model.Package;
import com.masai.model.PackageDto;
import com.masai.exception.PackageException;

public interface PackageService {

	public Package createPackage(Package pack ,String key) throws PackageException;
	
	public Package updatePackage(Package pack, String key)throws PackageException;
	
	public Package deletePackagebyId(Integer packageId,String key) throws PackageException;
	
	public Package viewPackageById(Integer packageId)throws PackageException;
	
	public List<Package> viewAllPackage() throws PackageException;
	
	

}

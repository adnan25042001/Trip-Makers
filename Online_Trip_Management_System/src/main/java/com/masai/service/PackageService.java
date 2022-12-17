package com.masai.service;

import java.util.List;
import com.masai.model.Package;

import com.masai.model.Package;
import com.masai.exception.PackageException;
import com.masai.model.*;
public interface PackageService {

	public Package addPackage(Package pack ,String key) throws PackageException;
	public Package deletePackagebyId(Integer packageId,String key) throws PackageException;
	public Package viewPackageById(Integer packageId)throws PackageException;
	public List<Package> viewAllPackage() throws PackageException;
	public PackageDto bookPackage(Integer packageId, String key)throws PackageException;
	public String removePackage(Integer packageId,String key)throws PackageException;
}

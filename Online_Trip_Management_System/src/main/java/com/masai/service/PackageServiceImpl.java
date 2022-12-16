package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.masai.exception.PackageException;
import com.masai.repository.PackageRepository;

@Service
public class PackageServiceImpl implements PackageService{

	public PackageRepository pdao;
	
	@Override
	public Package addPackage(Package pack) throws PackageException {
		Package p =pdao.save(pack);
		if(p!=null) return p;
		throw new PackageException("Packege not added");
	}

	@Override
	public Package deletePackagebyId(Integer packageId) throws PackageException {
		
		Optional<Package> p = pdao.findById(packageId);
		if(p== null) throw new PackageException("Package with "+packageId +" is not available");
		Package deletedpackage = p.get();
		return deletedpackage;
	}

	@Override
	public Package viewPackageById(Integer packageId) throws PackageException {
		
		Optional<Package> view = pdao.findById(packageId);
		
		if(view.isEmpty() ) throw new PackageException("Package with packaId : "+packageId +" is not available");
		
		Package viewPackage = view.get();
		return viewPackage;
	}

	@Override
	public List<Package> viewAllPackage() throws PackageException {
		
		List <Package> all = pdao.findAll();
		
		if(all.isEmpty()) throw new PackageException("No packages are found");
		
		return all;
	}

}

package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.model.Package;
import com.masai.model.PackageDto;
import com.masai.exception.CustomerException;
import com.masai.exception.PackageException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.repository.CustomerDao;
import com.masai.repository.PackageRepository;
import com.masai.repository.UserSessionDao;

@Service
public class PackageServiceImpl implements PackageService{
    @Autowired   
	public PackageRepository pdao;
	
	@Autowired
	private UserSessionDao uSesDao;

	@Autowired
	private CustomerDao cdao;
	
	

	@Override
	public Package addPackage(Package pack,String key) throws PackageException {
		
		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);
		if (optCurrcustomer.isEmpty()) {
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);
		}
		
		Package p =pdao.save(pack);
		if(p!=null) return p;
		throw new PackageException("Packege not added");
	}

	@Override
	public Package deletePackagebyId(Integer packageId,String key) throws PackageException {
		
		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);
		if (optCurrcustomer.isEmpty()) {
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);
		}
		
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

	@Override
	public PackageDto bookPackage(Integer packageId, String key) throws PackageException {
		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);
		if (optCurrcustomer.isEmpty()) {
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);
		}
		
		Customer customer = cdao.findByEmail(optCurrcustomer.get().getEmail()).orElseThrow(()-> new CustomerException("Customer does not exist") );
		
		Package p= pdao.findById(packageId).orElseThrow(()-> new PackageException("PAckage does not exist with packageId:- "+packageId));
		
		customer.getPackages().add(p);
		p.getCustomer().add(customer);
		
      cdao.save(customer);
      pdao.save(p);
		
	 PackageDto packagedto = pdao.getPackageDto(packageId);
		return packagedto;
	}

	@Override
	public String removePackage(Integer packageId, String key) throws PackageException {
		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);
		if (optCurrcustomer.isEmpty()) {
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);
		}
		
		Customer customer = cdao.findByEmail(optCurrcustomer.get().getEmail()).orElseThrow(()-> new CustomerException("Customer does not exist") );
		
		Package p= pdao.findById(packageId).orElseThrow(()-> new PackageException("PAckage does not exist with packageId:- "+packageId));
		
		customer.getPackages().remove(p);
		return "Removed Package Successfully";
	}

	

}

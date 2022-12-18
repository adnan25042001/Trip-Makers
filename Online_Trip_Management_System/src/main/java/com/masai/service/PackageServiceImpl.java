package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.model.Package;
import com.masai.model.UserType;
import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.PackageException;
import com.masai.model.CurrentUserSession;
import com.masai.repository.PackageDao;
import com.masai.repository.UserSessionDao;

@Service
public class PackageServiceImpl implements PackageService {

	@Autowired
	public PackageDao pdao;

	@Autowired
	private UserSessionDao uSesDao;

	@Override
	public Package createPackage(Package pack, String key) throws PackageException {

		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);

		if (optCurrcustomer.isEmpty())
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);

		if (optCurrcustomer.get().getUserType().equals(UserType.CUSTOMER))
			throw new AdminException("Invalid AuthKey : " + key);

		Package p = pdao.save(pack);

		if (p != null)
			return p;
		throw new PackageException("Packege not added");
	}

	@Override
	public Package deletePackagebyId(Integer packageId, String key) throws PackageException {

		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);

		if (optCurrcustomer.isEmpty())
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);

		if (optCurrcustomer.get().getUserType().equals(UserType.CUSTOMER))
			throw new AdminException("Invalid AuthKey : " + key);

		Optional<Package> p = pdao.findById(packageId);

		if (p == null)
			throw new PackageException("Package with " + packageId + " is not available");

		Package deletedpackage = p.get();

		pdao.delete(deletedpackage);

		return deletedpackage;
	}

	@Override
	public Package viewPackageById(Integer packageId) throws PackageException {

		Optional<Package> view = pdao.findById(packageId);

		if (view.isEmpty())
			throw new PackageException("Package with packaId : " + packageId + " is not available");

		Package viewPackage = view.get();
		return viewPackage;

	}

	@Override
	public List<Package> viewAllPackage() throws PackageException {

		List<Package> all = pdao.findAll();

		if (all.isEmpty())
			throw new PackageException("No packages are found");

		return all;

	}

	@Override
	public Package updatePackage(Package pack, String key) throws PackageException {

		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);

		if (optCurrcustomer.isEmpty())
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);

		Optional<Package> getPkg = pdao.findById(pack.getPackageId());
		if (!getPkg.isPresent()) {
			throw new PackageException("Package Not Found with Id : " + pack.getPackageId());
		}
		Package packageUpdated = pdao.save(pack);
		return packageUpdated;

	}

}

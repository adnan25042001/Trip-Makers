package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.exception.PackageException;
import com.masai.model.Package;
import com.masai.model.PackageDto;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer>{

	@Query("select new com.masai.Package(s.packageId,s.packageName,s.packageType,s.packageDescription,s.packageCost) from Package p where p.packageId=?1")
	public PackageDto getPackageDto(Integer id)throws PackageException;
	
}

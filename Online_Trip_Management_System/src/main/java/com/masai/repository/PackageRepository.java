package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Package;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer>{

}

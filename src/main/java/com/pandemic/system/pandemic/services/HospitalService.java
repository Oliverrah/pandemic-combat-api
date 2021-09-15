package com.pandemic.system.pandemic.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pandemic.system.pandemic.entities.Hospital;

public interface HospitalService {

	public List<Hospital> findAll(Pageable pageable);
	public Hospital findById(Long id);
	public Hospital save(Hospital hospital);
	public void deleteById(Long id);
	public int percentageOfOccupancyGreaterThanNinety();
	public int percentageOfoccupancyLowerThanNinety();
	public Hospital longerCrowdedHospital();
	public Hospital lessTimeCrowdedHospital();
}

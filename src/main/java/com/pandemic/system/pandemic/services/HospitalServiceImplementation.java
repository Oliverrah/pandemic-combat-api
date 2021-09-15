package com.pandemic.system.pandemic.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pandemic.system.pandemic.entities.Hospital;
import com.pandemic.system.pandemic.entities.Resource;
import com.pandemic.system.pandemic.exceptions.HospitalNotFoundException;
import com.pandemic.system.pandemic.repositorys.HospitalRepository;

@Service
public class HospitalServiceImplementation implements HospitalService {

	@Autowired
	HospitalRepository hospitalRepository;
	
	@Autowired
	ResourcesService resourcesService;
	
	@Override
	public List<Hospital> findAll(Pageable pageable) {
		return hospitalRepository.findAll();
	}

	@Override
	public Hospital findById(Long id) {
		Optional<Hospital> hospital =  hospitalRepository.findById(id);
		
		if(hospital.isPresent()) {
		return hospital.get();	
		}
		
		else {
			throw new HospitalNotFoundException("Hospital id not found - " + id);
		}
	}
	
	@Override
	@Transactional
	public Hospital save(Hospital hospital) {
	
		for(Resource resource : hospital.getResources()) {
			hospital.add(resource);
		}
	
		hospital.calculateOccupancyPercentage();
		return hospitalRepository.save(hospital);
	}
	
	@Override
	public void deleteById(Long id) {
		hospitalRepository.deleteById(id);		
	}
	
	@Override
	public int percentageOfOccupancyGreaterThanNinety() {
		List<Hospital> hospitals = hospitalRepository.findAll();
		
		int size = hospitals.size();
		int numberOfHospitals = 0;
		
		for(Hospital hospital : hospitals) {
			int occupancy = hospital.getPercentageOfOccupancy();
			if(occupancy > 90) {
				numberOfHospitals += 1;
			}
		}		
		return (numberOfHospitals * 100) / size;	
	}

	@Override
	public int percentageOfoccupancyLowerThanNinety() {
		List<Hospital> hospitals = hospitalRepository.findAll();
		
		int size = hospitals.size();
		int numberOfHospitals = 0;
		
		for(Hospital hospital : hospitals) {
			int occupancy = hospital.getPercentageOfOccupancy();
			if(occupancy < 90) {
				numberOfHospitals += 1;
			}
		}		
		return (numberOfHospitals * 100) / size;	
	}

	@Override
	public Hospital longerCrowdedHospital() {
		List<Hospital> hospitals = hospitalRepository.findAll();
	
		List<Hospital> result =  hospitals.stream().filter(hospital -> hospital.getPercentageOfOccupancy() > 90).collect(Collectors.toList());
	
		Hospital longerHospital = null;
		Date redFlagDate = new Date();
		
		for(Hospital hospital : result) {
						
			if(hospital.getRedFlag().before(redFlagDate)) {
				redFlagDate = hospital.getRedFlag();
				longerHospital = hospital;
			}
		}	
		return longerHospital;
	}

	@Override
	public Hospital lessTimeCrowdedHospital() {
		List<Hospital> hospitals = hospitalRepository.findAll();
		
		List<Hospital> result =  hospitals.stream().filter(hospital -> hospital.getPercentageOfOccupancy() > 90).collect(Collectors.toList());
		
		Hospital lessTimeHospital = null;
		Date redFlagDate = new Date(0);
		
		for(Hospital hospital : result) {
			
			if(hospital.getRedFlag().after(redFlagDate)) {
				
				redFlagDate = hospital.getRedFlag();
				lessTimeHospital = hospital;
			}
		
		}	
		return lessTimeHospital;
	}
}
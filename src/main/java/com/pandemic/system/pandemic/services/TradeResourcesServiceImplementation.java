package com.pandemic.system.pandemic.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pandemic.system.pandemic.dtos.TradeResourceDTO;
import com.pandemic.system.pandemic.entities.Hospital;
import com.pandemic.system.pandemic.entities.Resource;
import com.pandemic.system.pandemic.entities.TradeResources;
import com.pandemic.system.pandemic.exceptions.ResourcePointsNotEquals;
import com.pandemic.system.pandemic.repositorys.HospitalRepository;
import com.pandemic.system.pandemic.repositorys.ResourcesRepository;
import com.pandemic.system.pandemic.repositorys.TradeResourceRepository;

@Service
public class TradeResourcesServiceImplementation implements TradeResourcesService {

	@Autowired
	TradeResourceRepository tradeResourceRepository;
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	@Autowired
	ResourcesRepository resourcesRepository;
	
	public void exchangeResources(TradeResourceDTO tradeResourceDTO){
		
		TradeResources tradeResource = dtoToEntity(tradeResourceDTO);
		
		Optional<Hospital> hospitalGiving =hospitalRepository.findById(tradeResourceDTO.getHospitalGivingId());
		
		Optional<Hospital> hospitalReceiving =hospitalRepository.findById(tradeResourceDTO.getHospitalReceivingId()); 
		

		if(hospitalGiving.get().getPercentageOfOccupancy() > 90 || hospitalReceiving.get().getPercentageOfOccupancy() > 90) {		
			tradeResources(tradeResourceDTO);
			tradeResourceRepository.save(tradeResource);			
			return;
		}
		
		if(tradeResource.isResourcePointsEquals()) {			
			tradeResources(tradeResourceDTO);
			tradeResourceRepository.save(tradeResource);
		}
		else {
			throw new ResourcePointsNotEquals("Os recursos não tem a mesma quantidade de pontos");
		}
	}
	
	private TradeResources tradeResources(TradeResourceDTO tradeResourceDTO) {
		TradeResources tradeResource = dtoToEntity(tradeResourceDTO);
		for(Resource resource : tradeResource.getResourcesGiving()) {
			Hospital hospital = hospitalRepository.getById(tradeResourceDTO.getHospitalReceivingId());
			resource.setHospital(hospital);
			resourcesRepository.save(resource);
		}
		
		for(Resource resource : tradeResource.getResourcesReceiving()) {
			Hospital hospital = hospitalRepository.getById(tradeResourceDTO.getHospitalGivingId());
			resource.setHospital(hospital);
			resourcesRepository.save(resource);
		}
		
		return tradeResource;
	}
	
	
	private TradeResources dtoToEntity(TradeResourceDTO tradeResourceDTO) {
		List<Resource> resourcesGiving = new ArrayList<>();
		List<Resource> resourcesReceiving = new ArrayList<>();
		TradeResources tradeResourceEntity = new TradeResources();
		for(Long id : tradeResourceDTO.getResourcesGivingIds()) {
			Optional<Resource> resourceSearched = resourcesRepository.findById(id);
			if(!resourceSearched.isEmpty()) {
				resourcesGiving.add(resourceSearched.get());
			}
			tradeResourceEntity.setResourcesGiving(resourcesGiving);
		}
		for(Long id : tradeResourceDTO.getResourcesReceivingIds()) {
			Optional<Resource> resourceSearched = resourcesRepository.findById(id);
			if(!resourceSearched.isEmpty()) {
				resourcesReceiving.add(resourceSearched.get());
			}
			tradeResourceEntity.setResourcesReceiving(resourcesReceiving);
		}
		tradeResourceEntity.setHospitalGivingId(tradeResourceDTO.getHospitalGivingId());
		tradeResourceEntity.setHospitalReceivingId(tradeResourceDTO.getHospitalReceivingId());
		
		return tradeResourceEntity;
	}
}
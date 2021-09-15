package com.pandemic.system.pandemic.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pandemic.system.pandemic.entities.Resource;
import com.pandemic.system.pandemic.exceptions.ResourceNotFoundException;
import com.pandemic.system.pandemic.repositorys.ResourcesRepository;

@Service
public class ResourcesServiceImplementation implements ResourcesService {

	@Autowired
	ResourcesRepository resourcesRepository;
	
	@Override
	public List<Resource> findAll() {
		return resourcesRepository.findAll();
	}

	@Override
	public Resource findById(Long id) {
		Optional<Resource> resources = resourcesRepository.findById(id);
		
		if(resources.isPresent()) {
			return resources.get();		
		}
		else {
			throw new ResourceNotFoundException("Resource id not found - " + id);
		}
	}

	@Override
	@Transactional
	public Resource save(Resource resources) {
		return resourcesRepository.save(resources);
	}
}
package com.pandemic.system.pandemic.services;

import java.util.List;

import com.pandemic.system.pandemic.entities.Resource;

public interface ResourcesService {

	public List<Resource> findAll();
	public Resource findById(Long id);
	public Resource save(Resource resources);
}

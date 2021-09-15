package com.pandemic.system.pandemic.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pandemic.system.pandemic.entities.Resource;

public interface ResourcesRepository extends JpaRepository<Resource, Long> {

}
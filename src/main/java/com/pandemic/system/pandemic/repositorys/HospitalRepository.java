package com.pandemic.system.pandemic.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pandemic.system.pandemic.entities.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}
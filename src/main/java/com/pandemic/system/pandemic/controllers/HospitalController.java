package com.pandemic.system.pandemic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pandemic.system.pandemic.entities.Hospital;
import com.pandemic.system.pandemic.services.HospitalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Hospital EndPoints")
public class HospitalController {

	@Autowired
	HospitalService hospitalService;
	
	@ApiOperation("Retornar todos os hospitais registrados")
	@GetMapping(value = "/hospitals", produces ={"application/json"})
	public List<Hospital> findAll(@RequestParam(value="page", defaultValue ="0") int page,
								  @RequestParam(value="limit", defaultValue ="15") int limit,
								  @RequestParam(value="direction", defaultValue ="ASC") String direction){

		Direction orderDirection;
		
		if("DESC".equalsIgnoreCase(direction)) {
		orderDirection = Direction.DESC;
		}
		else if("ASC".equalsIgnoreCase(direction)) {
		orderDirection = Direction.ASC;
		}
		
		else {
		throw new RuntimeException("Invalid Direction " + direction);
		}
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(orderDirection, "name"));
		
		return hospitalService.findAll(pageable);
	}
	
	@ApiOperation("Procurar hospital por ID")
	@GetMapping(value = "/hospitals/{hospitalId}", produces ={"application/json"})
	public Hospital findById(@PathVariable Long hospitalId) {		
		return hospitalService.findById(hospitalId);
	}
	
	@ApiOperation("Cadastrar novo hospital")
	@PostMapping(value = "/hospitals", produces ={"application/json"} ,consumes ={"application/json"})
	public Hospital save(@RequestBody Hospital hospital) {	
		return hospitalService.save(hospital);	
	}
	
	@ApiOperation("Deletar hospital por ID")
	@DeleteMapping("/hospitals/{hospitalId}")
	public void deleteById(@PathVariable Long hospitalId) {
		hospitalService.deleteById(hospitalId);
	}
}
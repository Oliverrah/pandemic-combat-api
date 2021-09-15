package com.pandemic.system.pandemic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandemic.system.pandemic.entities.Report;
import com.pandemic.system.pandemic.services.HospitalService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Relatório EndPoint")
public class ReportController {
	
	@Autowired
	HospitalService hospitalService;
	
	@GetMapping("/report")
	public Report getReport() {
		Report report = new Report(hospitalService.percentageOfOccupancyGreaterThanNinety(), hospitalService.percentageOfoccupancyLowerThanNinety(), hospitalService.longerCrowdedHospital(), hospitalService.lessTimeCrowdedHospital());
			
		return report;
	}
}
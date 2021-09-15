package com.pandemic.system.pandemic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandemic.system.pandemic.dtos.TradeResourceDTO;
import com.pandemic.system.pandemic.services.ResourcesService;
import com.pandemic.system.pandemic.services.TradeResourcesService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Troca de recursos EndPoint")
public class TradeResourcesController {

	@Autowired
	ResourcesService resourcesService;
	
	@Autowired
	TradeResourcesService tradeResourcesSevice;
	
	@PutMapping("/trade")
	public void tradeResources(@RequestBody TradeResourceDTO tradeResourcesdto){
		
		tradeResourcesSevice.exchangeResources(tradeResourcesdto);
		
	}
}

package com.pandemic.system.pandemic.entities;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TradeResourcesTest {

	private TradeResources tradeResources;
	
	@BeforeEach
	void setup() {
		List<Resource> listOne = new ArrayList<>();
		for(int i = 1; i <= 10; i++) {			
			Resource resource = new Resource();
			resource.setPoints(1);
			listOne.add(resource);
		}	
		
		List<Resource> listTwo = new ArrayList<>();
		for(int i = 1; i <= 10; i++) {			
			Resource resource = new Resource();
			resource.setPoints(1);
			listTwo.add(resource);
		}	
		
	
		this.tradeResources = new TradeResources();
		this.tradeResources.setResourcesGiving(listOne);
		this.tradeResources.setResourcesReceiving(listTwo);
	}
	
	@Test
	void should_Return_True_When_Resources_Are_Equal() {
		
	//when
		boolean result = this.tradeResources.isResourcePointsEquals();
		
	//then
		assertTrue(result);
	}

}

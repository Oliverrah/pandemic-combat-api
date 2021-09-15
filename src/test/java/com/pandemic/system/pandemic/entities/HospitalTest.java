package com.pandemic.system.pandemic.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HospitalTest {

	private Hospital hospital;
	
	@BeforeEach
	void setup() {
		List<Resource> resources = new ArrayList<>();
		for(int i = 1; i <= 91; i++) {
			Resource resource = new Resource();
			resource.setPoints(1);
			resources.add(resource);
		}	
		this.hospital = new Hospital("HospitalTeste", "AdressTeste", "123456789123456", 12,12,100,resources);
	}
	
	@Test
	void should_Sum_All_Resources_Points() {			
		
		//when
		int total = this.hospital.generateResourcePoints();
		
		//then		
		assertEquals(91, total);
	}
	
	@Test
	void should_Calculte_Percentage_Of_Occupancy() {
		
		//when
		this.hospital.calculateOccupancyPercentage();
				
		//then
		assertEquals(91, this.hospital.getPercentageOfOccupancy());		
	}
	
	@Test
	void should_Generate_RedFlag_When_Occupancy_Greater_Than_90() {
		
		//given
		this.hospital.calculateOccupancyPercentage();
		
		//when
		this.hospital.generateRedFlag();
		
		//then
		assertNotNull(this.hospital.getRedFlag());
		
	}
}

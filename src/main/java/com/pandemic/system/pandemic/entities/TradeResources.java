package com.pandemic.system.pandemic.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class TradeResources {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private Long hospitalGivingId;
		
	private Long hospitalReceivingId;
			
	@ManyToMany
	@JoinTable(name="trade_resources_resources", 
	   joinColumns =  @JoinColumn(name="trade_resources_id"),
	   inverseJoinColumns =  @JoinColumn(name="resources_id"))
	private List<Resource> resourcesGiving;
	
	@ManyToMany
	@JoinTable(name="trade_resources_resources", 
	   joinColumns =  @JoinColumn(name="trade_resources_id"),
	   inverseJoinColumns =  @JoinColumn(name="resources_id"))
	private List<Resource> resourcesReceiving;
	
	private LocalDateTime createdDate;
	
	
	public TradeResources() {
		this.createdDate = LocalDateTime.now();
	}
	
	public TradeResources(Long id, Long hospitalGivingId, Long hospitalReceivingId) {
		this.id = id;
		this.hospitalGivingId = hospitalGivingId;
		this.hospitalReceivingId = hospitalReceivingId;
		this.createdDate = LocalDateTime.now();
	}

	public boolean isResourcePointsEquals() {
		int totalListOne = 0;
		for(Resource resource : this.getResourcesGiving()) {
			totalListOne += resource.getPoints();
		}
		
		int totalListTwo = 0;
		for(Resource resource : this.getResourcesReceiving()) {
			totalListTwo += resource.getPoints();
		}
				
		if(totalListOne - totalListTwo != 0) {
			return false;
		}		
		return true;	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHospitalGivingId() {
		return hospitalGivingId;
	}

	public void setHospitalGivingId(Long hospitalGivingId) {
		this.hospitalGivingId = hospitalGivingId;
	}


	public Long getHospitalReceivingId() {
		return hospitalReceivingId;
	}


	public void setHospitalReceivingId(Long hospitalReceivingId) {
		this.hospitalReceivingId = hospitalReceivingId;
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public List<Resource> getResourcesGiving() {
		return resourcesGiving;
	}

	public void setResourcesGiving(List<Resource> resourcesGiving) {
		this.resourcesGiving = resourcesGiving;
	}

	public List<Resource> getResourcesReceiving() {
		return resourcesReceiving;
	}

	public void setResourcesReceiving(List<Resource> resourcesReceiving) {
		this.resourcesReceiving = resourcesReceiving;
	}
}
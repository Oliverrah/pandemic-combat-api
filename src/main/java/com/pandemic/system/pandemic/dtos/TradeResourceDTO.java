package com.pandemic.system.pandemic.dtos;

import java.util.List;

public class TradeResourceDTO {
	
	private Long hospitalGivingId;
		
	private Long hospitalReceivingId;
	
	private List<Long> resourcesGivingIds;
	
	private List<Long> resourcesReceivingIds;
		
	public TradeResourceDTO() {
		
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

	public List<Long> getResourcesGivingIds() {
		return resourcesGivingIds;
	}

	public void setResourcesGivingIds(List<Long> resourcesGivingIds) {
		this.resourcesGivingIds = resourcesGivingIds;
	}

	public List<Long> getResourcesReceivingIds() {
		return resourcesReceivingIds;
	}

	public void setResourcesReceivingIds(List<Long> resourcesReceivingIds) {
		this.resourcesReceivingIds = resourcesReceivingIds;
	}
}
package com.pandemic.system.pandemic.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pandemic.system.pandemic.entities.enums.ResourceType;

@Entity
@Table(name = "resources")
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="points")
	private int points;
	
	@Column(name="resource_type")
	@Enumerated(EnumType.STRING)
	private ResourceType resourceType;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hospital_id")
	@JsonIgnore
	private Hospital hospital;
	
	@ManyToMany
	@JoinTable(name="trade_resources_resources", 
	   joinColumns =  @JoinColumn(name="resources_id"),
	   inverseJoinColumns =  @JoinColumn(name="trade_resources_id"))
	@JsonIgnore
	private List<TradeResources> tradeResouces;
	
	public Resource() {
		
	}

	public Resource(Long id, String name, int points, ResourceType resourceType, Hospital hospital) {
		this.id = id;
		this.name = name;
		this.points = points;
		this.resourceType = resourceType;
		this.hospital = hospital;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	
	public List<TradeResources> getTradeResouces() {
		return tradeResouces;
	}

	public void setTradeResouces(List<TradeResources> tradeResouces) {
		this.tradeResouces = tradeResouces;
	}

	@Override
	public String toString() {
		return "Resources [id=" + id + ", name=" + name + ", points=" + points + ", resourceType=" + resourceType
				+ ", hospital=" + hospital + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hospital == null) ? 0 : hospital.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + points;
		result = prime * result + ((resourceType == null) ? 0 : resourceType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resource other = (Resource) obj;
		if (hospital == null) {
			if (other.hospital != null)
				return false;
		} else if (!hospital.equals(other.hospital))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (points != other.points)
			return false;
		if (resourceType != other.resourceType)
			return false;
		return true;
	}	
}
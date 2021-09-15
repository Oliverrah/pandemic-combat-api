package com.pandemic.system.pandemic.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "hospital")
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "cnpj")
	private String cnpj;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "latitude")
	private double latitude;
	
	@Column(name = "longitude")
	private double longitude;
	
	@Column(name = "occupancy_capacity")
	private int occupancyCapacityInPoints;
	
	@Column(name = "percentage_of_occupancy")
	@ApiModelProperty(hidden = true)
	private int percentageOfOccupancy;
	
	@Column(name = "red_flag")
	@ApiModelProperty(hidden = true)
	private Date redFlag;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "hospital_id")
	private List<Resource> resources;
	
	public Hospital() {
		this.createdDate = new Date();
	}

	public Hospital(String name, String address, String cnpj, double latitude,
			double longitude, int occupancyCapacityInPoints,
			List<Resource> resources) {
		this.name = name;
		this.address = address;
		this.cnpj = cnpj;
		this.createdDate = new Date();
		this.latitude = latitude;
		this.longitude = longitude;
		this.occupancyCapacityInPoints = occupancyCapacityInPoints;
		this.resources = resources;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public int getOccupancyCapacityInPoints() {
		return occupancyCapacityInPoints;
	}

	public void setOccupancyCapacityInPoints(int occupancyCapacityInPoints) {
		this.occupancyCapacityInPoints = occupancyCapacityInPoints;
	}

	public int getPercentageOfOccupancy() {
		return percentageOfOccupancy;
	}

	public void setPercentageOfOccupancy(int percentageOfOccupancy) {
		this.percentageOfOccupancy = percentageOfOccupancy;
	}

	public Date getRedFlag() {
		return redFlag;
	}

	public void setRedFlag(Date redFlag) {
		this.redFlag = redFlag;
	}

	public List<Resource> getResources() {
		return resources;
	}
	
	public void add(Resource resource) {	
		resource.setHospital(this);
	}
	
	public int generateResourcePoints() {
		int total = 0;
		for(Resource resource : this.resources) {
			total += resource.getPoints();
		}		
		return total;
	}
	
	public void calculateOccupancyPercentage() {		
		this.percentageOfOccupancy =  (generateResourcePoints() * 100) / getOccupancyCapacityInPoints();
		generateRedFlag();
	}
	
	public void generateRedFlag() {
		if (this.percentageOfOccupancy > 90) {
			this.redFlag = new Date();
		}
	}
	
	@Override
	public String toString() {
		return "Hospital [id=" + id + ", name=" + name + ", address=" + address + ", cnpj=" + cnpj + ", createdDate="
				+ createdDate + ", latitude=" + latitude + ", longitude=" + longitude + ", occupancyCapacityInPoints="
				+ occupancyCapacityInPoints + ", percentageOfOccupancy=" + percentageOfOccupancy + ", redFlag="
				+ redFlag + ", resources=" + resources + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + occupancyCapacityInPoints;
		result = prime * result + percentageOfOccupancy;
		result = prime * result + ((redFlag == null) ? 0 : redFlag.hashCode());
		result = prime * result + ((resources == null) ? 0 : resources.hashCode());
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
		Hospital other = (Hospital) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (occupancyCapacityInPoints != other.occupancyCapacityInPoints)
			return false;
		if (percentageOfOccupancy != other.percentageOfOccupancy)
			return false;
		if (redFlag == null) {
			if (other.redFlag != null)
				return false;
		} else if (!redFlag.equals(other.redFlag))
			return false;
		if (resources == null) {
			if (other.resources != null)
				return false;
		} else if (!resources.equals(other.resources))
			return false;
		return true;
	}
}
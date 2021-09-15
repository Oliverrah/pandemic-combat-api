package com.pandemic.system.pandemic.entities;

public class Report {

	private int percentageOfOccupancyGreaterThanNinety;
	
	private int percentageOfoccupancyLowerThanNinety;
	
	private Hospital longerCrowdedHospital;
	
	private Hospital lessTimeCrowdedHospital;

	
	public Report() {
		
	}
	
	public Report(int percentageOfOccupancyGreaterThanNinety, int percentageOfoccupancyLowerThanNinety,
			Hospital longerCrowdedHospital, Hospital lessTimeCrowdedHospital) {
		this.percentageOfOccupancyGreaterThanNinety = percentageOfOccupancyGreaterThanNinety;
		this.percentageOfoccupancyLowerThanNinety = percentageOfoccupancyLowerThanNinety;
		this.longerCrowdedHospital = longerCrowdedHospital;
		this.lessTimeCrowdedHospital = lessTimeCrowdedHospital;
	}

	public int getPercentageOfOccupancyGreaterThanNinety() {
		return percentageOfOccupancyGreaterThanNinety;
	}

	public void setPercentageOfOccupancyGreaterThanNinety(int percentageOfOccupancyGreaterThanNinety) {
		this.percentageOfOccupancyGreaterThanNinety = percentageOfOccupancyGreaterThanNinety;
	}

	public int getPercentageOfoccupancyLowerThanNinety() {
		return percentageOfoccupancyLowerThanNinety;
	}

	public void setPercentageOfoccupancyLowerThanNinety(int percentageOfoccupancyLowerThanNinety) {
		this.percentageOfoccupancyLowerThanNinety = percentageOfoccupancyLowerThanNinety;
	}

	public Hospital getLongerCrowdedHospital() {
		return longerCrowdedHospital;
	}

	public void setLongerCrowdedHospital(Hospital longerCrowdedHospital) {
		this.longerCrowdedHospital = longerCrowdedHospital;
	}

	public Hospital getLessTimeCrowdedHospital() {
		return lessTimeCrowdedHospital;
	}

	public void setLessTimeCrowdedHospital(Hospital lessTimeCrowdedHospital) {
		this.lessTimeCrowdedHospital = lessTimeCrowdedHospital;
	}	
}
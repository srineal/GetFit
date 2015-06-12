package model;

import java.io.Serializable;

public class Plan implements Serializable{

	private Double startingWeight, currentWeight, goalWeight, height;
	private String gender;
	private int plan, age;
	public Double getStartingWeight() {
		return startingWeight;
	}
	public void setStartingWeight(Double startingWeight) {
		this.startingWeight = startingWeight;
	}
	public Double getCurrentWeight() {
		return currentWeight;
	}
	public void setCurrentWeight(Double currentWeight) {
		this.currentWeight = currentWeight;
	}
	public Double getGoalWeight() {
		return goalWeight;
	}
	public void setGoalWeight(Double goalWeight) {
		this.goalWeight = goalWeight;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getPlan() {
		return plan;
	}
	public void setPlan(int plan) {
		this.plan = plan;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}

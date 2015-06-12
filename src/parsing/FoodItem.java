package parsing;

import java.io.Serializable;
/*Mohammed Jalal Hemidach
 * */
public class FoodItem implements Serializable{
	

	String mealsName, ndbno, name, weight, measure, protein, sugar, carbs, fat, energy;
	

	public String getMealsName() {
		return mealsName;
	}

	public void setMealsName(String mealsName) {
		this.mealsName = mealsName;
	}

	public String getNdbno() {
		return ndbno;
	}

	public void setNdbno(String ndbno) {
		this.ndbno = ndbno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getProtein() {
		return protein;
	}

	public void setProtein(String protein) {
		this.protein = protein;
	}
	
	public String getSugar() {
		return sugar;
	}

	public void setSugar(String sugar) {
		this.sugar = sugar;
	}


	public String getCarbs() {
		return carbs;
	}

	public void setCarbs(String carbs) {
		this.carbs = carbs;
	}

	public String getFat() {
		return fat;
	}

	public void setFat(String fat) {
		this.fat = fat;
	}

	public String getEnergy() {
		return energy;
	}

	public void setEnergy(String energy) {
		this.energy = energy;
	}

	@Override
	public String toString() {
		return "FoodItem [mealsName=" + mealsName + ", ndbno=" + ndbno
				+ ", name=" + name + ", weight=" + weight + ", measure="
				+ measure + ", protein=" + protein + ", sugar=" + sugar
				+ ", carbs=" + carbs + ", fat=" + fat + ", energy=" + energy
				+ "]";
	}

}

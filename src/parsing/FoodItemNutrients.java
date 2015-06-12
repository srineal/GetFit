package parsing;

public class FoodItemNutrients {

	String ndbno, name, protein, sugar, carbs, fat, energy;
	

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
		return "FoodItemNutrients [ndbno=" + ndbno + ", name=" + name
				+ ", protein=" + protein + ", sugar=" + sugar + ", carbs="
				+ carbs + ", fat=" + fat + ", energy=" + energy + "]";
	}
}

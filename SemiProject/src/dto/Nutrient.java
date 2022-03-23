package dto;

public class Nutrient {
	private int nutrientNo;
	private String nutrient;
	private String explain;
	
	@Override
	public String toString() {
		return "Nutrient [nutrientNo=" + nutrientNo + ", nutrient=" + nutrient + ", explain=" + explain + "]";
	}
	public int getNutrientNo() {
		return nutrientNo;
	}
	public void setNutrientNo(int nutrientNo) {
		this.nutrientNo = nutrientNo;
	}
	public String getNutrient() {
		return nutrient;
	}
	public void setNutrient(String nutrient) {
		this.nutrient = nutrient;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
}

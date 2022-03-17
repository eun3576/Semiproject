package dto;

public class ProductCategory {
	
	private int category_no;
	private String category_name;
	private int product_no;
	@Override
	public String toString() {
		return "ProductCategory [category_no=" + category_no + ", category_name=" + category_name + ", product_no="
				+ product_no + "]";
	}
	public int getCategory_no() {
		return category_no;
	}
	public void setCategory_no(int category_no) {
		this.category_no = category_no;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public int getProduct_no() {
		return product_no;
	}
	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}
	
	
	
}

package dto;

public class Product {
	private int product_no;
	private String category_name;
	private String product_name;
	private String product_content;
	private int product_views;
	private String product_img;
	
	
	
	@Override
	public String toString() {
		return "Product [product_no=" + product_no + ", category_name=" + category_name + ", product_name="
				+ product_name + ", product_content=" + product_content + ", product_views=" + product_views
				+ ", product_img=" + product_img + "]";
	}
	public int getProduct_no() {
		return product_no;
	}
	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}
	
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_content() {
		return product_content;
	}
	public void setProduct_content(String product_content) {
		this.product_content = product_content;
	}
	public int getProduct_views() {
		return product_views;
	}
	public void setProduct_views(int product_views) {
		this.product_views = product_views;
	}
	public String getProduct_img() {
		return product_img;
	}
	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}
	
	
}

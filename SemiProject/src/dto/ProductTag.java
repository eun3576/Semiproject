package dto;

public class ProductTag {
	private int tagNo;
	private String tagName;
	private int productNo;
	
	
	@Override
	public String toString() {
		return "ProductTag [tagNo=" + tagNo + ", tagName=" + tagName + ", productNo=" + productNo + "]";
	}
	
	public int getTagNo() {
		return tagNo;
	}
	public void setTagNo(int tagNo) {
		this.tagNo = tagNo;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	
	

	
}

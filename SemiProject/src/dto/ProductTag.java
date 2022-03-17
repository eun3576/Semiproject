package dto;

public class ProductTag {
	private int tagNo;
	private String tagName;
	private int productNo;
	private int productViews; //메인에서 product의 조회수를 받아올 변수
	private String productImg; //메인에서 product의 이미지를 받아올 변수
	@Override
	public String toString() {
		return "ProductTag [tagNo=" + tagNo + ", tagName=" + tagName + ", productNo=" + productNo + ", productViews="
				+ productViews + ", productImg=" + productImg + "]";
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
	public int getProductViews() {
		return productViews;
	}
	public void setProductViews(int productViews) {
		this.productViews = productViews;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	
}

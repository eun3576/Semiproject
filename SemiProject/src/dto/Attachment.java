package dto;

public class Attachment {
	private int attachment_no;
	private int review_no;
	private int notice_no;
	private int inquiry_no;
	private int product_no;
	private int filesize;
	private String stored_img;
	private String origin_img;
	
	@Override
	public String toString() {
		return "Attachment [attachment_no=" + attachment_no + ", review_no=" + review_no + ", notice_no=" + notice_no
				+ ", inquiry_no=" + inquiry_no + ", product_no=" + product_no + ", filesize=" + filesize
				+ ", stored_img=" + stored_img + ", origin_img=" + origin_img + "]";
	}
	public int getAttachment_no() {
		return attachment_no;
	}
	public void setAttachment_no(int attachment_no) {
		this.attachment_no = attachment_no;
	}
	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public int getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}
	public int getInquiry_no() {
		return inquiry_no;
	}
	public void setInquiry_no(int inquiry_no) {
		this.inquiry_no = inquiry_no;
	}
	public int getProduct_no() {
		return product_no;
	}
	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}
	public String getStored_img() {
		return stored_img;
	}
	public void setStored_img(String stored_img) {
		this.stored_img = stored_img;
	}
	public String getOrigin_img() {
		return origin_img;
	}
	public void setOrigin_img(String origin_img) {
		this.origin_img = origin_img;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	
}

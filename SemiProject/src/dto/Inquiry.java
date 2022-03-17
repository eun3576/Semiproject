package dto;

import java.util.Date;

public class Inquiry {
	private int inquiry_no;
	private String title;
	private String content;
	private Date write_date;
	private int user_no;
	private String id;
	private int password;
	
	
	
	@Override
	public String toString() {
		return "Inquiry [inquiry_no=" + inquiry_no + ", title=" + title + ", content=" + content + ", write_date="
				+ write_date + ", user_no=" + user_no + ", id=" + id + ", password=" + password + "]";
	}
	public int getInquiry_no() {
		return inquiry_no;
	}
	public void setInquiry_no(int inquiry_no) {
		this.inquiry_no = inquiry_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	
	
}

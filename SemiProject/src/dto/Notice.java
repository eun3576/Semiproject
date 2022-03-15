package dto;

import java.util.Date;

public class Notice {
	private int notice_no;
	private String title;
	private String content;
	private Date write_date;
	private int manager_no;
	@Override
	public String toString() {
		return "Notice [notice_no=" + notice_no + ", title=" + title + ", content=" + content + "]";
	}
	public int getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
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
	public int getManager_no() {
		return manager_no;
	}
	public void setManager_no(int manager_no) {
		this.manager_no = manager_no;
	}
	
	
	
}

package dto;

import java.util.Date;

public class ManagerNotice {

	private int noticeNo;
	private String title;
	private String content;
	private Date writeDate;
	private int managerNo;
	
	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", title=" + title + ", content=" + content + ", writeDate=" + writeDate
				+ ", managerNo=" + managerNo + "]";
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
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
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public int getManagerNo() {
		return managerNo;
	}
	public void setManagerNo(int managerNo) {
		this.managerNo = managerNo;
	}
	
}
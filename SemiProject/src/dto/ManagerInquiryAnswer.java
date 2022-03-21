package dto;

import java.sql.Date;

public class ManagerInquiryAnswer {

	private int answerNo;
	private String content;
	private Date writeDate;
	private int managerNo;
	private int inquiryNo;
	
	@Override
	public String toString() {
		return "InquiryAnswer [answerNo=" + answerNo + ", content=" + content + ", writeDate=" + writeDate
				+ ", managerNo=" + managerNo + ", inquiryNo=" + inquiryNo + "]";
	}
	
	public int getAnswerNo() {
		return answerNo;
	}
	public void setAnswerNo(int answerNo) {
		this.answerNo = answerNo;
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
	public int getInquiryNo() {
		return inquiryNo;
	}
	public void setInquiryNo(int inquiryNo) {
		this.inquiryNo = inquiryNo;
	}
	
}	
package dto;

import java.util.Date;

public class Review {
	private int reviewNo;
	private String title;
	private String content;
	private Date writeDate;
	private Date updatedate;
	private int views;
	private int userNo;
	private String nickname; //메인에서 베스트 리뷰에 사용하는 닉네임입니다.
	
	//toString
	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", title=" + title + ", content=" + content + ", writeDate=" + writeDate
				+ ", updatedate=" + updatedate + ", views=" + views + ", userNo=" + userNo + ", nickname=" + nickname
				+ "]";
	}
	
	//getter and setters
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
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
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}

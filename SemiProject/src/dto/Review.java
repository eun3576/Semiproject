package dto;

import java.util.Date;

public class Review {
//<<<<<<< jyj
	
	private int review_no;
	private String title;
	private String content;
	private Date writeDate;
	private Date updateDate;
	private int views;
	private int user_no;
	private String nickname; //메인에서 베스트 리뷰에 사용하는 닉네임입니다.
	@Override
	public String toString() {
		return "Review [review_no=" + review_no + ", title=" + title + ", content=" + content + ", writeDate="
				+ writeDate + ", updateDate=" + updateDate + ", views=" + views + ", user_no=" + user_no + ", nickname="
				+ nickname + "]";
	}
	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
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
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}

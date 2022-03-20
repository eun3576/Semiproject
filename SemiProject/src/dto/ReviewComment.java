package dto;

import java.sql.Timestamp;

public class ReviewComment {

	private int comment_no;
	private int user_no;
	private int review_no;
	private String comment_text;
	private Timestamp comment_date;
	private Timestamp comment_update;
	//DTO nickname 추가
	private String nickname;
	
	@Override
	public String toString() {
		return "ReviewComment [comment_no=" + comment_no + ", user_no=" + user_no + ", review_no=" + review_no
				+ ", comment_text=" + comment_text + ", comment_date=" + comment_date + ", comment_update="
				+ comment_update + ", nickname=" + nickname + "]";
	}
	
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public String getComment_text() {
		return comment_text;
	}
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}
	public Timestamp getComment_date() {
		return comment_date;
	}
	public void setComment_date(Timestamp comment_date) {
		this.comment_date = comment_date;
	}
	public Timestamp getComment_update() {
		return comment_update;
	}
	public void setComment_update(Timestamp comment_update) {
		this.comment_update = comment_update;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
		
}

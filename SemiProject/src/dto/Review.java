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
	
	@Override
	public String toString() {
		return "Review [review_no=" + review_no + ", title=" + title + ", content=" + content + ", writeDate="
				+ writeDate + ", updateDate=" + updateDate + ", views=" + views + ", user_no=" + user_no + "]";
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
=======
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
//>>>>>>> main
	}
	
}

package dto;

import java.util.Date;

public class ManagerReviewComment {

	private int commentNo;
	private int userNo;
	private String commentText;
	private Date commentDate;
	private Date commentUpdate;
	private int reviewNo;
	
	@Override
	public String toString() {
		return "ReviewComment [commentNo=" + commentNo + ", userNo=" + userNo + ", commentText=" + commentText
				+ ", commentDate=" + commentDate + ", commentUpdate=" + commentUpdate + ", reviewNo=" + reviewNo + "]";
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public Date getCommentUpdate() {
		return commentUpdate;
	}

	public void setCommentUpdate(Date commentUpdate) {
		this.commentUpdate = commentUpdate;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	
}

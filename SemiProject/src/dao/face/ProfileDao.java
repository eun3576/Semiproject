package dao.face;

import java.sql.Connection;


import dto.Inquiry;
import dto.Profile;
import dto.Review;
import dto.UserInfo;
import dto.ReviewComment;



public interface ProfileDao {
	

	
	public Profile selectProfile(Connection conn , Profile profile);
	
	
	public int updateProfile(Connection conn , Profile profile);
	
	
	public int deleteProfile(Connection conn , UserInfo userInfo);
	
	//유저정보테이블
	public UserInfo selectProfile(Connection conn , UserInfo userInfo);  
	
	//질문테이블
	public int deleteInquiry(Connection conn , Inquiry inquiry);
	
	//리뷰테이블
	public int deleteReview(Connection conn , Review review);

	//리뷰 코멘트
	public int deleteReComment(Connection conn , ReviewComment comment);
	
	
	
}



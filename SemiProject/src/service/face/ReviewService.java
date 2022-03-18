package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Attachment;
import dto.Review;
import dto.UserInfo;


public interface ReviewService {
	
	/*
	 * 게시글 전체 조회
	 * 
	 * @return - 게시글 전체 조회 결과 목록
	 * 
	 */
	public List<Review> getList();
	
	/**
	 * 게시글의 따른 작성자의 닉네임과 증상 전체 조회
	 * 
	 * @return - 게시글을 작성한 작성자 닉네임과 증상 전체 조회
	 */
	public List<UserInfo> getNickSympList();
	
	/**
	 * 요청 파라미터 얻어오기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Review - 전달 파라미터 review값을 포함한 DTO객체
	 */
	public Review getReviewno(HttpServletRequest req);
	
	/**
	 * 전달된 review_no를 이용하여 작성자의 증상과 nickname 정보를 조회한다.
	 * 
	 * @param review - 조회할 review_no를 가지고 있는 DTO객체
	 * @return UserInfo - 조회된 유저의 정보
	 * 
	 */
	public UserInfo getNickSymptonByReviewno(Review reviewno);
	
	/**
	 * 전달된 review_no을 이용하여 게시글을 조회한다.
	 * 
	 * 조회된 게시글의 조회수를 1 증가 시킨다.
	 * 
	 * @param review - 조회할 reviewno를 가지고 있는 DTO객체
	 * @return Review - 조회된 게시글 정보
	 */
	public Review view(Review reviewno);
	
	
	/**
	 * 후기 작성 - 후기 작성 페이지에서의 입력 데이터 삽입 
	 * 
	 * @param req - 요청정보 객체(게시글 내용 + 파일(파일첨부기능 나중에 추가))
	 */
	public void write(HttpServletRequest req);
	
	/**
	 * 첨부파일 정보 조회하기 
	 * 
	 * @param review - 첨부파일과 연결된 후기글의 DTO객체
	 * @return Attachment - 첨부파일 정보 DTO 객체
	 */
	public Attachment viewFile(Review review);
	
	/**
	 * 후기 수정 하기 - 후기 수정 페이지에서 입력 데이터 삽입
	 * 
	 * @param req - 요청정보 객체(게시글 내용 + 파일(파일첨부기능 나중에 추가))
	 */
	public void update(HttpServletRequest req);
	
	/**
	 * 후기글 삭제
	 * 
	 * @param board - 삭제할 게시글 번호를 가진 객체
	 */
	public void delete(Review review);
	
	/**
	 * @param reviewno
	 * @return
	 */
//	public ReviewComment viewComment(Review reviewno);
	
}

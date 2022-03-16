package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Review;

public interface ReviewService {
	
	/*
	 * 게시글 전체 조회
	 * 
	 * @return - 게시글 전체 조회 결과 목록
	 * 
	 */
	public List<Review> getList();
	
	/**
	 * 요청 파라미터 얻어오기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Review - 전달 파라미터 review값을 포함한 DTO객체
	 */
	public Review getReviewno(HttpServletRequest req);
	
	/**
	 * 전달된 reviewno을 이용하여 게시글을 조회한다.
	 * 
	 * 조회된 게시글의 조회수를 1 증가 시킨다.
	 * 
	 * @param review - 조회할 reviewno를 가지고 있는 DTO객체
	 * @return Review - 조회된 게시글 정보
	 */
	public Review view(Review review);
	
	/**
	 * 후기 작성 - 후기 작성 페이지에서의 입력 데이터 삽입 
	 * 
	 * @param req - 요청정보 객체(게시글 내용 + 파일(파일첨부기능 나중에 추가))
	 */
	public void write(HttpServletRequest req);
	
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
	
}

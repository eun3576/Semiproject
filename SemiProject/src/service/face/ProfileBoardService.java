package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Review;
import util.Paging;

public interface ProfileBoardService {
	
	/**
	 * 쓴글만 조회
	 * 
	 * @param req
	 * @return
	 */
	
	public List<Review> Blist(HttpServletRequest req);
	
	/**
	 * 
	 * 쓴 글만 페이징 목록 조회
	 * 
	 * 
	 * @param paging - 페이징 정보객체
	 * @return List<review> - 페이징이 반영된 게시글 조회 결과 목록
	 */
	
	public List<Review> Blist(HttpServletRequest req, Paging paging);

	/**
	 * 페이징 객체 생성
	 * @param req - 요청 정보 객체
	 * @return Paging
	 */
	
	public Paging getPaging(HttpServletRequest req);

//	
//	/**
//	 * 요청 파라미터 얻기
//	 * 
//	 * @param req
//	 * @return
//	 */
//	
//	public Review getReviewno(Review review);
//	
//	

	

	

}

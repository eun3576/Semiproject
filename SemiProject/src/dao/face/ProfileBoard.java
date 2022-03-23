package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Review;
import dto.UserInfo;
import util.Paging;

public interface ProfileBoard {
	
	
	/**
	 * 게시글 조회
	 * 
	 * @param conn
	 * @param userinfo
	 * @return
	 */
	public List<Review> Blist(Connection conn , UserInfo userinfo);

	/**
	 * 게시글 조회 - 페이징 처리 추가
	 * 
	 * @param conn
	 * @param userinfo
	 * @return
	 */
	public List<Review> Blist(Connection conn , UserInfo userinfo, Paging paging);
	
	
	/**
	 *  게시글 수 조회
	 * 
	 * @param conn DB연결객체
	 * @return int - ProfileBoard 전체 행 수
	 */
	public int selectCntAll(Connection conn, UserInfo userinfo);

//	/**
//	 * 리뷰글 조회
//	 * 
//	 * @param conn
//	 * @param review
//	 * @return
//	 */
//	
//	public Review selectReview(Connection conn , Review review );
//	
//	
	

}

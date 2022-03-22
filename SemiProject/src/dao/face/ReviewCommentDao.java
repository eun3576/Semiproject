package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.ManagerReviewComment;
import util.Paging;

public interface ReviewCommentDao {

	/**
	 * <ReviewComment> 테이블 전체 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return List<ReviewComment> - <ReviewComment>테이블 전체 조회 결과 목록
	 */
	public List<ManagerReviewComment> selectAll(Connection conn);
	

	/**
	 * ReviewComment 테이블 전체 조회
	 * -> 페이징 처리 추가
	 * 
	 * @param conn - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<ReviewComment> - ReviewComment테이블 전체 조회 결과 목록
	 */
	public List<ManagerReviewComment> selectAll(Connection conn, Paging paging); 
	/**
	 * 총 댓글 수 조회 
	 * 
	 * @param conn - DB연결 객체
	 * @return int - ReviewComment테이블의 전체 행 수
	 */
	public int selectCntAll(Connection conn);
	
	/**
	 * 게시글 삭제
	 * 
	 * @param ManagerReviewComment - 삭제할 댓글번호를 담은 객체
	 */
	public int delete(Connection conn, ManagerReviewComment reviewComment);


	public List<ManagerReviewComment> selectAll(Connection conn, Paging paging, String search);

}

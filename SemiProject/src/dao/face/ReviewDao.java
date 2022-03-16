package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Review;

public interface ReviewDao {
	
	/**
	 * Review 테이블 전체 조회
	 * @param conn - DB 연결 객체
	 * @return List<Review> - Review 테이블 전체 조회 결과 목록
	 */
	public List<Review> selectAll(Connection conn);
	
	/**
	 * 지정된 reviewno의 후기글 조회하기
	 * 
	 * @param conn - DB연결 객체
	 * @param reviewno - 조회할 후기글의 reviewno를 가진 DTO 객체
	 * @return Review - 조회된 후기글의 상세정보 DTO 객체
	 */
	public Review selectReviewByReviewno(Connection conn, Review reviewno);
	
	/**
	 * @param conn - DB연결 객체
	 * @param reviewno - 조회할 후기글의 reviewno를 가진 DTO 객체
	 * @return int - UPDATE 쿼리 수행 결과
	 */
	public int updateView(Connection conn, Review reviewno);
	
	/**
	 * 작성자가 입력한 게시글을 삽입!
	 * 
	 * @param conn - DB 연결 객체
	 * @param review - 작성한 내용을 저장할 DTO 객체
	 * @return - 삽입 완료: 1, 삽입 실패: 0
	 */
	public int insert(Connection conn, Review review);
	
	/**
	 * 작성자가 수정한 입력글 삽입
	 * 
	 * @param conn - DB 연결 객체
	 * @param review - 수정할 내용을 담은 DTO 객체
	 * @return
	 */
	public int update(Connection conn, Review review);
	
	/**
	 * 후기글 삭제
	 * 
	 * @param conn - DB 연결 객체
	 * @param review - 삭제할 게시글 번호 담은 객체
	 * @return
	 */
	public int delete(Connection conn, Review review);
}

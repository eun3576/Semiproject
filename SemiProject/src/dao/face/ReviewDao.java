package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Attachment;
import dto.Review;
import dto.ReviewComment;
import dto.UserInfo;

public interface ReviewDao {
	
	/**
	 * Review 테이블 전체 조회
	 * @param conn - DB 연결 객체
	 * @return List<Review> - Review 테이블 전체 조회 결과 목록
	 */
	public List<Review> selectReviewAll(Connection conn);
	
	/**
	 * review의 user_no컬럼과 userinfo의 user_no컬럼과 같은
	 * nickname와 sympton list 조회
	 * 
	 * @param conn - DB 연결 객체 
	 * @return List<UserInfo> - review에 따른 작성자 list
	 * 
	 */
	public List<UserInfo> selectNickSympAll(Connection conn);
	
	/**
	 * 지정된 reviewno의 후기글 조회하기
	 * 
	 * @param conn - DB연결 객체
	 * @param reviewno - 조회할 후기글의 reviewno를 가진 DTO 객체
	 * @return Review - 조회된 후기글의 상세정보 DTO 객체
	 */
	public Review selectReviewByReviewno(Connection conn, Review reviewno);
	
	/**
	 * review_no컬럼을 이용하여 nickname, sympton 컬럼 조회
	 *  
	 * @param conn - DB 연결 객체
	 * @param reviewno - 조회할 reviewno를 가진 객체
	 * @return Userinfo - 작성자 유저 정보
	 */
	public UserInfo selectNickSympByReviewno(Connection conn, Review reviewno);
	
	/** 
	 * 시퀀스를 이용하여 다음 후기글 번호를 조회한다.
	 * 
	 * @param conn - DB연결 객체 
	 * @return int - 다음 후기글 번호
	 */
	public int selectReivewno(Connection conn);
	
	/**
	 * nickname을 통하여 user_no조회한다.
	 * 
	 * @param conn - DB연결 객체
	 * @return UserInfo - nickname에 대한 userno
	 */
	public UserInfo selectUsernoByNick(Connection conn, UserInfo userInfo);
	
	/**
	 * 첨부파일 삽입 
	 * 
	 * @param conn - DB 연결 객체 
	 * @param attach - 첨부파일 정보
	 * @return int - INSERT 쿼리 수행 결과
	 */
	public int insertFile(Connection conn, Attachment attach);
	
	/**
	 * 첨부파일 정보 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param reviewno - 조회할 후기글 번호가 있는 DTO객체 
	 * @return Attachment - 첨부파일 정보
	 */
	public Attachment selectFile(Connection conn, Review review);
	
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
	
	/**
	 * 후기글에 첨부된 파일 기록 삭제
	 * 
	 * @param conn - DB연결 객체 
	 * @param review - 삭제할 후기글 번호를 담은 객체 
	 * @return int - 쿼리문 수행 결과 
	 */
	public int deleteFile(Connection conn, Review review);
	
	/**
	 * review_no를 통하여 ReviewComment 정보 조회
	 * 
	 * @param conn - DB 연결 객체
	 * @param reviewno - review_no가 저장 되어있는 review DTO 객체
	 * @return ReviewComment - 조회된 ReviewComment의 데이터 객체 
	 */
	public List<ReviewComment> selectReviewCommentByReviewno(Connection conn, Review reviewno);
	
	
	/**
	 * review_no를 통하여 ReviewComment에 삽입
	 * 
	 * @param conn - DB 연결 객체
	 * @param reviewno - review_no가 저장 되어있는 review DTO 객체
	 * @return int - 쿼리문 수행 결과
	 */
	public int insertComment(Connection conn, ReviewComment reviewComment);
	
}

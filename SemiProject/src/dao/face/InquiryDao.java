package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Inquiry;
import dto.InquiryAnswer;
import dto.UserInfo;
import util.Paging;

public interface InquiryDao {
	
	/**
	 * 페이지에 해당하는 문의글 리스트를 가져온다
	 * @param conn DB연결 객체
	 * @param paging 페이징 정보 객체
	 * @return 해당 페이지의 문의글 리스트
	 */
	public List<Inquiry> getInquiryList(Connection conn, Paging paging);
	
	/**
	 * 전체 문의글 개수를 가져온다
	 * @param conn DB연결 객체
	 * @return 전체 문의글 개수
	 */
	public int inquiryCntAll(Connection conn);
	
	/**
	 * 문의글 작성
	 * @param conn DB연결 객체
	 * @param inquiry 작성된 문의글 DTO
	 */
	public void insertInquiry(Connection conn, Inquiry inquiry);
	
	/**
	 * 선택한 문의글의 정보를 가져온다
	 * @param conn DB연결 객체
	 * @param inquiry 문의글을 식별할 수 있는 데이터
	 * @return 해당되는 문의글 DTO
	 */
	public Inquiry selectInquiry(Connection conn, Inquiry inquiry);
	
	/**
	 * 해당하는 문의글의 답변 정보를 가져온다
	 * @param conn DB연결 객체
	 * @param inquiryAnswer 문의글을 식별할 수 있는 데이터
	 * @return 해당되는 답변 DTO
	 */
	public InquiryAnswer selectInquiryAnswer(Connection conn, InquiryAnswer inquiryAnswer);
	
	/**
	 * 선택한 문의글을 삭제
	 * @param conn DB연결 객체
	 * @param inquiry 문의글을 식별할 수 있는 데이터
	 */
	public void deleteInquiry(Connection conn, Inquiry inquiry);
	
	/**
	 * 선택한 문의글의 답변 삭제
	 * @param conn DB연결 객체
	 * @param inquiry 문의글을 식별할 수 있는 데이터
	 */
	public void deleteInquiryAnswer(Connection conn, Inquiry inquiry);
	
	/**
	 * 회원 아이디를 통해 회원 정보를 가져온다
	 * @param conn DB연결 객체
	 * @param userInfo 회원을 식별 할 수 있는 데이터
	 * @return 해당되는 회원 정보
	 */
	public UserInfo selectUserNoByUserId(Connection conn, UserInfo userInfo);
}



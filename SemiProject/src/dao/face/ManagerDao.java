package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Manager;
import dto.Review;
import dto.UserInfo;

public interface ManagerDao {

	/**
	 * id와 password가 일치하는 관리자를 조회한다
	 * -> 로그인 인증에 사용
	 * 
	 * @param conn - DB연결 객체
	 * @param manager - 조회할 관리자 정보
	 * @return - 0 : 존재하지 않는 , 1 : 존재하는
	 */
	public int selectCntManagerByIdPassword(Connection conn, Manager manager);

	/**
	 * id를 이용해 관리자 정보 조회하기
	 * 
	 * @param conn - DB연결 객체
	 * @param manager - 조회할 id를 가진 객체
	 * @return Manager - 조회된 관리자 정보
	 */
	public Manager selectManagerById(Connection conn, Manager manager);

	/* 회원 정보 조회 */
	public List<UserInfo> selectUserList(Connection conn);
	
	/* 회원 정보 삭제 */
	public void userDelete(Connection conn, UserInfo userInfo);
	
	/* 회원 닉네임 검색 */
	public List<UserInfo> selectUserSearchList(Connection conn, String search);
	
	/* 리뷰 게시글 삭제 */
	public void reviewDelete(Connection conn, Review review);
	
	
}

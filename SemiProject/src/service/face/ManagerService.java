package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Manager;
import dto.UserInfo;

public interface ManagerService {
	
	/**
	 * 로그인 정보 추출하기
	 * 
	 * @param req
	 * @return
	 */
	public Manager getLoginManager(HttpServletRequest req);
	
	/**
	 * 로그인 인증처리
	 * 
	 * @param manager - 로그인 정보
	 * @return boolean - true : 인증 성공 / false : 인증 실패
	 */
	public boolean login(Manager manager);

	/**
	 * 관리자 정보 가져오기
	 * 
	 * @param manager - 조회할 관리자 아이디를 가진 객체
	 * @return - 조회된 관리자 정보
	 */
	public Manager info(Manager manager);
	
	/* 회원 정보 조회 */
	public List<UserInfo> selectUserList();
	
	/* 회원 정보 삭제 */
	public void userDelete(HttpServletRequest req);
	
	/* 회원 정보 검색  */
	public List<UserInfo> searchUserList(String req);
	
	/* 리뷰 게시글 삭제 */
	public void reviewDelete(HttpServletRequest req);
	
	

}

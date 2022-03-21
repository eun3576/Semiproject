package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.Manager;

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
	
	

}

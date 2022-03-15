package service.face;

import dto.UserInfo;

public interface UserLoginService {

	/**
	 * 파라미터로 로그인하기
	 * @param user 파라미터를 받은 user객체
	 * @return 성공1, 실패0
	 */
	int login(UserInfo user);

	/**
	 * 아이디로 회원 객체 가져오기
	 * @param user userid
	 * @return user객체
	 */ 
	UserInfo info(UserInfo user);

}

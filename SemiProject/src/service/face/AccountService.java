package service.face;

import dto.UserInfo;

public interface AccountService {

	/**
	 * 가입된 회원정보인지 확인한다
	 * @param user 확인할 회원정보
	 * @return true:새로운 회원, false:기존 회원
	 */
	public boolean userCheck(UserInfo user);

	/**
	 * 회원가입을 한다
	 * @param user 회원가입할 객체
	 * @return 회원삽입결과
	 */
	public int insertUser(UserInfo user);


}

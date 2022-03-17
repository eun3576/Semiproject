package service.face;

import javax.servlet.http.HttpServletRequest;

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

	/**
	 * 아이디를 매개변수로 받아 저장한다
	 * @param req 객체에 저장할 매개변수
	 * @return 저장한 user객체
	 */
	public UserInfo getUserJoinId(HttpServletRequest req);

	/**
	 * 회원가입시 기존의 아이디인지 확인한다
	 * @param user 확인할 아이디
	 * @return 기존 아이디일시 화면에 표시할 String
	 */
	public String userIdCheck(UserInfo user);

	/**
	 * 닉네임을 메개변수로 받아 저장한다
	 * @param req 객체에 저장할 매개변수
	 * @return 저장한 user객체
	 */
	public UserInfo getUserJoinNick(HttpServletRequest req);

	/**
	 * 회원가입시 기존의 닉네임인지 확인한다
	 * @param user 확인할 닉네임
	 * @return 기존 닉네임일시 화면에 표시할 String
	 */
	public String userNickCheck(UserInfo user);



}

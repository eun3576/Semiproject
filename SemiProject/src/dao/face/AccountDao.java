package dao.face;

import java.sql.Connection;

import dto.UserInfo;

public interface AccountDao {

	/**
	 * 가입된 id인지 확인한다
	 * @param conn db연결객체
	 * @param user 확인할 user객체
	 * @return true:새로운 회원, false:기존 회원
	 */
	public boolean userIdCheck(Connection conn, UserInfo user);
	/**
	 * 가입된 닉네임인지 확인한다
	 * @param conn db연결객체
	 * @param user 확인할 user객체
	 * @return true:새로운 회원, false:기존 회원
	 */
	public boolean userNickCheck(Connection conn, UserInfo user);

	/**
	 * 회원객체를 회원테이블에 저장한다
	 * @param conn db연결객체
	 * @param user 저장할 user객체
	 * @return 성공:1, 실패:0
	 */
	public int insertUser(Connection conn, UserInfo user);

}

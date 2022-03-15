package dao.face;

import java.sql.Connection;

import dto.UserInfo;

public interface UserLoginDao {

	/**
	 * 회원인지 아닌지 확인
	 * @param conn db연결객체
	 * @param user 확인할 user객체
	 * @return 성공 1, 실패 0
	 */
	int selectCntMemberByUseridUserpw(Connection conn, UserInfo user);
 
	/**
	 * id로 user객체 가져오기
	 * @param conn db연결객체
	 * @param user 확인할 user객체
	 * @return UserInfo
	 */
	UserInfo selectUserInfoByUserId(Connection conn, UserInfo user);

}

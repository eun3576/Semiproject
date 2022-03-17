package service.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.AccountDao;
import dao.impl.AccountDaoImpl;
import dto.UserInfo;
import service.face.AccountService;

public class AccountServiceImpl implements AccountService{
	private AccountDao accountDao = new AccountDaoImpl();
	@Override
	public boolean userCheck(UserInfo user) {
		Connection conn = JDBCTemplate.getConnection();
		if(accountDao.userIdCheck(conn,user)==true&&accountDao.userNickCheck(conn,user)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int insertUser(UserInfo user) {
		Connection conn = JDBCTemplate.getConnection();
		int insertCnt = 0;
		insertCnt = accountDao.insertUser(conn,user);
		if(insertCnt>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return insertCnt;
	}

	@Override
	public UserInfo getUserJoinId(HttpServletRequest req) {
		UserInfo user = new UserInfo();
		user.setId(req.getParameter("userJoinid"));

		return user;
	}

	@Override
	public String userIdCheck(UserInfo user) {
		Connection conn = JDBCTemplate.getConnection();
		String str = null;
		if(accountDao.userIdCheck(conn, user)==false) {
			str="사용중인 아이디입니다";
		}
		return str;
	}

	@Override
	public UserInfo getUserJoinNick(HttpServletRequest req) {
		UserInfo user = new UserInfo();
		user.setNickname(req.getParameter("userJoinnick"));
		return user;
	}

	@Override
	public String userNickCheck(UserInfo user) {
		Connection conn = JDBCTemplate.getConnection();
		String str = null;
		if(accountDao.userNickCheck(conn, user)==false) {
			str="사용중인 닉네임입니다";
		}
		return str;
	}

}

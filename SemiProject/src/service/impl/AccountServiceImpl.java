package service.impl;

import java.sql.Connection;

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

}

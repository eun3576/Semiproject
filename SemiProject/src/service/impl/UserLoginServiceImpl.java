package service.impl;

import java.sql.Connection;

import common.JDBCTemplate;
import dao.face.UserLoginDao;
import dao.impl.UserLoginDaoImpl;
import dto.UserInfo;
import service.face.UserLoginService;

public class UserLoginServiceImpl implements UserLoginService{
	UserLoginDao userLoginDao = new UserLoginDaoImpl();
	@Override
	public int login(UserInfo user) {
		Connection conn = JDBCTemplate.getConnection();
		return userLoginDao.selectCntMemberByUseridUserpw(conn, user);
	}
	@Override
	public UserInfo info(UserInfo user) {
		Connection conn = JDBCTemplate.getConnection();
		return userLoginDao.selectUserInfoByUserId(conn, user);
	}
 
}

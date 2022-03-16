package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.AccountDao;
import dto.UserInfo;

public class AccountDaoImpl implements AccountDao{
	
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public boolean userIdCheck(Connection conn, UserInfo user) {
		String sql = "";
		sql += "SELECT count(*) FROM userinfo";
		sql += " WHERE id=?";
		
		//결과값 받을 변수 
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getId());
			rs = ps.executeQuery();
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			if(rs!=null)JDBCTemplate.close(rs);
//			if(ps!=null)JDBCTemplate.close(ps);
		}
		
		if(cnt<=0) {
			return true;
		} //if
			return false;

	}
	
	@Override
	public boolean userNickCheck(Connection conn, UserInfo user) {
		String sql = "";
		sql += "SELECT count(*) FROM userinfo";
		sql += " WHERE nickname=?";
		
		//결과값 받을 변수 
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getNickname());
			rs = ps.executeQuery();
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			if(rs!=null)JDBCTemplate.close(rs);
//			if(ps!=null)JDBCTemplate.close(ps);
		}
		
		if(cnt<=0) {
			return true;
		} //if
			return false;

	}

	@Override
	public int insertUser(Connection conn, UserInfo user) {
		String sql = "";
		sql += "INSERT INTO userinfo(USER_NO, ID, PASSWORD, GENDER, SYMPTON, PHONENUMBER)";
		sql += " VALUES(user_seq.nextval, ?, ?, ?, ?, ?)";
		
		int cnt = 0; 
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getGender());
			ps.setString(4, user.getNickname());
			ps.setString(5, user.getPhonenumber());
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return cnt;
	}
}

package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.UserLoginDao;
import dto.UserInfo;

public class UserLoginDaoImpl implements UserLoginDao{
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public int selectCntMemberByUseridUserpw(Connection conn, UserInfo user) {
		String sql = "";
		sql += "SELECT count(*) FROM userinfo";
		sql += " WHERE id=? ";
		sql += " AND password = ?";
		 
		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getId());
			ps.setString(2, user.getPassword());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return cnt;
		
	}//selectCntMemberByUseridUserpw

	@Override
	public UserInfo selectUserInfoByUserId(Connection conn, UserInfo user) {

		String sql = "";
		sql += "SELECT id, nickname";
		sql += " FROM userinfo";
		sql += " WHERE id = ?";
		
		//조회 결과를 저장할 DTO
		UserInfo result = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getId());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				result = new UserInfo();
				
				result.setId( rs.getString("id") );
				result.setNickname( rs.getString("nickname") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
	
}//UserLoginDaoImpl

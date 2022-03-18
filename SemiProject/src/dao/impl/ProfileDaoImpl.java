package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ProfileDao;
import dto.Profile;

public class ProfileDaoImpl implements ProfileDao{
	
	//회원정보 수정 메소드

	@Override
	public Profile selectProfile(Connection conn, Profile profile) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "select * from userinfo";
		sql += " where id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, profile.getId());
			
			rs = ps.executeQuery();
		
			while (rs.next() ) {
				
				profile.setNickname(rs.getString("nickname"));
				profile.setPhonenumber(rs.getString("phonenumber"));
				profile.setPassword(rs.getString("password"));
				
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
			
			
		}
		
		
		return profile;
	}

	//업데이터 프로필
	@Override
	public int updateProfile(Connection conn, Profile profile) {
		PreparedStatement ps = null;
		
		int res = 0;
		
		//변경된  sql 넣기
		
		String sql = "";
		sql += "update userinfo";
		sql += " set nickname = ? , phonenumber = ? , password = ? ";
		sql += " where id = ? ";
		
		
		
		try {
			ps = conn.prepareStatement(sql);
			
			
			ps.setString(1, profile.getNickname());
			ps.setString(2, profile.getPhonenumber());
			ps.setString(3, profile.getPassword());
			ps.setString(4, profile.getId());
			
			res = ps.executeUpdate();
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			//객체 닫기
			JDBCTemplate.close(ps);
			
		}
		
		
		
		return res;
	}
	
}

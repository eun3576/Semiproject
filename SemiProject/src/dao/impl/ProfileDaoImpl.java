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
		sql += "updata userinfo";
		sql += " set ";
		
		boolean okinfo = false;
		
		if (profile.getNickname()!= null) {
			sql += "nickname = ? ";
			okinfo = true;
	
		} else {
			okinfo = false;
		}
		
		if (okinfo) { sql += " , "; }
		
		
		if (profile.getPhonenumber()!= null) {
			sql += "phonenumber = ? ";
			
		} else {
			okinfo = false;
		}
		
		if (okinfo) { sql += " , "; }
		
		if (profile.getPassword()!= null) {
			sql += "password = ? ";
			
		} else {
			okinfo = false;
		}
		
		
		
		sql += " where id = ? ";
		
		List<String> infoList = new ArrayList<>();
		
		if (sql.contains("nickname")) {
			infoList.add("nickname");
			
		}
		
		if (sql.contains("phonenumber")) {
			infoList.add("phonenumber");
			
		}
		
		if (sql.contains("password")) {
			infoList.add("password");
			
		}
	
		try {
			
		for (int i=0; i<infoList.size(); i++) {
			
			if ("nickname".equals(infoList.get(i) ) ) {
				
					ps.setString(i+1, profile.getNickname());
				
			}
			if ("phonenumer".equals(infoList.get(i) ) ) {
				
				ps.setString(i+1, profile.getPhonenumber());
				
			}
			if ("password".equals(infoList.get(i) ) ) {
				
				ps.setString(i+1, profile.getPassword());
				
			}
		}
		
		
		ps.setString(infoList.size()+1, profile.getId());
		
		
		res = ps.executeUpdate();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(ps);
			
		}
		
		return res;
	}
	
}

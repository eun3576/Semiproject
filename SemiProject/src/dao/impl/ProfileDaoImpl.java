package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ProfileDao;
import dto.Inquiry;
import dto.Profile;
import dto.Review;
import dto.ReviewComment;
import dto.UserInfo;

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
	
	@Override
	public UserInfo selectProfile(Connection conn, UserInfo userInfo) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "select * from userinfo";
		sql += " where id =?";
		
		
		UserInfo info = new UserInfo();
				
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, userInfo.getId());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				info.setUserNo(rs.getInt("userno"));
				info.setPassword(rs.getString("password"));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			
		}
		
		return info;
	}
	
	
	@Override
	public int deleteProfile(Connection conn, UserInfo userinfo) {
		
		PreparedStatement ps = null;
		
		int res = 0;
		
		String sql = "";
		sql += " delete userinfo ";
		sql += " where id = ? ";
		 
		try {
			
			ps = conn.prepareStatement(sql);
			
//			System.out.println(userinfo.getId());
			
			ps.setString(1,	userinfo.getId());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			JDBCTemplate.close(ps);
			
		}
				
		
		
		return res;
	}
	
	@Override
	public int deleteInquiry(Connection conn, Inquiry inquiry) {
		
		
		PreparedStatement ps = null;
		
		int res = 0;
		
		String sql = "";
		sql += " delete inquiry ";
		sql += " where user_no = ? ";
		 
		try {
			
			ps = conn.prepareStatement(sql);
			
//			System.out.println(inquiry.getId());
			
			ps.setInt(1,inquiry.getUser_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			JDBCTemplate.close(ps);
			
		}
			
		return res;
	}
	
	@Override
	public int deleteReview(Connection conn, Review review) {
		
		PreparedStatement ps = null;
		
		int res = 0;
		
		String sql = "";
		sql += " delete review ";
		sql += " where user_no = ? ";
		 
		try {
			
			ps = conn.prepareStatement(sql);
			
//			System.out.println(review.get());
			
			ps.setInt(1,review.getUser_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			JDBCTemplate.close(ps);
			
		}
		
		
		return res;
	}
	
	@Override
	public int deleteReComment(Connection conn, ReviewComment comment) {
		
		PreparedStatement ps = null;
		
		int res = 0;
		
		String sql = "";
		sql += " delete review_comment ";
		sql += " where user_no = ? ";
		 
		try {
			
			ps = conn.prepareStatement(sql);
			
//			System.out.println(inquiry.getId());
			
			ps.setInt(1, comment.getUser_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			JDBCTemplate.close(ps);
			
		}
		
		return res;
	}
	
}


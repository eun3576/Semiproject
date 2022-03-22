package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ManagerDao;
import dto.Manager;
import dto.Review;
import dto.UserInfo;

public class ManagerDaoImpl implements ManagerDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public int selectCntManagerByIdPassword(Connection conn, Manager manager) {

		String sql = "";
		sql += " SELECT COUNT(*) FROM MANAGER ";
		sql += " WHERE ID =  ? ";
		sql += " AND password = ? ";
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, manager.getId());
			ps.setString(2, manager.getPassword());
			
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
	}
	
	@Override
	public Manager selectManagerById(Connection conn, Manager manager) {
		
		String sql = "";
		sql += " SELECT ID, PASSWORD, MANAGER_NO ";
		sql += " FROM MANAGER ";
		sql += " WHERE ID = ? ";
		sql += " AND PASSWORD = ? ";
		
		//조회 결과를 저장할 DTO
		Manager result = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, manager.getId());
			ps.setString(2, manager.getPassword());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				result = new Manager();
				
				result.setId(rs.getString("id") );
				result.setPassword(rs.getString("password") );
				result.setManager_no(rs.getInt("MANAGER_NO") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return result;
		
	}
	
	@Override
	public List<UserInfo> selectUserList(Connection conn) {
		
		String sql = "select * from userinfo order by user_no asc";
				
		//결과 저장할 List
		List<UserInfo> userList = new ArrayList<>();  
		
		try {
			ps = conn.prepareStatement(sql); 
			rs = ps.executeQuery(); 
			
			while( rs.next() ) {
				UserInfo n = new UserInfo(); 
				
				n.setUserNo(rs.getInt("user_no"));
				n.setId(rs.getString("id"));
				n.setPassword(rs.getString("password"));
				n.setGender(rs.getString("gender"));
				n.setNickname(rs.getNString("nickname"));
				n.setSymptom(rs.getNString("sympton"));
				n.setPhonenumber(rs.getNString("phonenumber"));
				
				userList.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return userList;
			
	}

	@Override
	public void userDelete(Connection conn, UserInfo userInfo) {
			
		String sql = "delete userinfo where user_no=?";		
	
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userInfo.getUserNo());
			
			res = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		if(res>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
	}

	@Override
	public List<UserInfo> selectUserSearchList(Connection conn, String search) {
		
		String sql = "select * from userinfo where id like ?";
		
		//결과 저장할 List
		List<UserInfo> userList = new ArrayList<>();  
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,"%" + search + "%");
			rs = ps.executeQuery(); 
			
			while( rs.next() ) {
				UserInfo n = new UserInfo(); 
				
				n.setUserNo(rs.getInt("user_no"));
				n.setId(rs.getString("id"));
				n.setPassword(rs.getString("password"));
				n.setGender(rs.getString("gender"));
				n.setNickname(rs.getNString("nickname"));
				n.setSymptom(rs.getNString("sympton"));
				n.setPhonenumber(rs.getNString("phonenumber"));
				
				userList.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return userList;
	}

	@Override
	public void reviewDelete(Connection conn, Review review) {
		
		String sql = "delete review where review_no=?";		
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, review.getReview_no());
			
			res = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		if(res>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
	}
		
}

package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ProfileBoard;
import dto.Review;
import dto.UserInfo;
import util.Paging;

public class ProfileBoardImpl implements ProfileBoard{
	
	@Override
	public List<Review> Blist(Connection conn, UserInfo userinfo) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "select review_no , title , views , write_date";
		sql += " from userinfo u";
		sql += " inner join review r ";
		sql += " on u.user_no = r.user_no";
		sql += " 	where u.id = ? ";
		sql += " order by write_date DESC";
		
		List<Review> Blist = new ArrayList<>();
		
		
		try {
			ps = conn.prepareStatement(sql);
//			System.out.println(userinfo.getId());
			
			//유저넘버 가져오기
			ps.setString(1, userinfo.getId());
			
			//결과저장
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Review review = new Review();
				
				review.setTitle(rs.getString("title"));
				review.setViews(rs.getInt("views"));
				review.setWriteDate(rs.getDate("write_date"));
				
				Blist.add(review);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			
		}
	
		return Blist;
	}

	@Override
	public int selectCntAll(Connection conn, UserInfo userinfo) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql +="select count(*) cnt";
		sql +=" from userinfo u";
		sql +=" inner join review r";
		sql +=" on u.user_no = r.user_no";
		sql +=" 	where u.id = ? ";
		sql +=" order by write_date";
		
		int count = 0;
		
		try {
			ps = conn.prepareStatement(sql);
//			System.out.println(userinfo.getId());
			
			//유저넘버 가져오기
			ps.setString(1, userinfo.getId());
			
			//결과저장
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				count = rs.getInt("cnt");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			
		}
	
		return count;
	}
	

	
	@Override
	public List<Review> Blist(Connection conn, UserInfo userinfo, Paging paging) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "select * from (";
		sql += "	select rownum rnum, R.* from (";
		sql += "	select review_no , title , views , write_date";
		sql += "	    from userinfo u";
		sql += "		inner join review r";
		sql += "		on u.user_no = r.user_no";
		sql += "		where u.id = ? ";
		sql += " 	order by write_date";
		sql += "  ) R";
		sql += " )review";
		sql += " where rnum between ? and ?";
		
		int count = 0;
		
		List<Review> Blist = new ArrayList<>();
				
		
		try {
			ps = conn.prepareStatement(sql);
//			System.out.println(userinfo.getId());
			
			//유저넘버 가져오기
			ps.setString(1, userinfo.getId());
			
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			
			//결과저장
			rs = ps.executeQuery();
			
				while (rs.next()) {
				
				Review review = new Review();
				
				review.setTitle(rs.getString("title"));
				review.setViews(rs.getInt("views"));
				review.setWriteDate(rs.getDate("write_date"));
				review.setReview_no(rs.getInt("review_no"));
				
//				System.out.println("테스트");
				
				
				Blist.add(review);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			
		}
	
		return Blist;
	}
	

}

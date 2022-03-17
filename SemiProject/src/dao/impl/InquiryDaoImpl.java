package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.InquiryDao;
import dto.Inquiry;
import dto.InquiryAnswer;
import dto.UserInfo;
import util.Paging;

public class InquiryDaoImpl implements InquiryDao{
	@Override
	public List<Inquiry> getInquiryList(Connection conn, Paging paging) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "select * from (";
		sql += "	select rownum rnum, t.* from (";
		sql += "		select inquiry_no, title, content, write_date, id, i.password from";
		sql += "		inquiry i, userinfo u";
		sql += "		where i.user_no = u.user_no";
		sql += "		order by inquiry_no desc";
		sql += "	) t ";
		sql += ") inquiry ";
		sql += "where rnum between ? and ?";
		
		List<Inquiry> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Inquiry inquiry = new Inquiry();
				inquiry.setInquiry_no(rs.getInt("inquiry_no"));
				inquiry.setTitle(rs.getString("title"));
				inquiry.setContent(rs.getString("content"));
				inquiry.setWrite_date(rs.getDate("write_date"));
				inquiry.setId(rs.getString("id"));
				inquiry.setPassword(rs.getInt("password"));
				
				list.add(inquiry);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
	
		return list;
	}
	
	@Override
	public int inquiryCntAll(Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT count(*) cnt FROM inquiry";
		
		int count = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				count = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return count;
	}
	
	@Override
	public void insertInquiry(Connection conn, Inquiry inquiry) {
		PreparedStatement ps = null;
		int res = 0;
		
		String sql = "";
		sql += "INSERT INTO inquiry(inquiry_no, title, content, user_no, password) ";
		sql += "VALUES(inquiry_seq.nextval, ?, ?, ?, ?)";
		
		System.out.println(inquiry);
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, inquiry.getTitle());
			ps.setString(2, inquiry.getContent());
			ps.setInt(3, inquiry.getUser_no());
			ps.setInt(4, inquiry.getPassword());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		if(res > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	
	@Override
	public UserInfo selectUserNoByUserId(Connection conn, UserInfo userInfo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT * FROM userinfo ";
		sql += "WHERE id=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userInfo.getId());
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				userInfo.setUserNo(rs.getInt("user_no"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return userInfo;
	}
	
	@Override
	public Inquiry selectInquiry(Connection conn, Inquiry inquiry) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT * FROM inquiry ";
		sql += "WHERE inquiry_no=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, inquiry.getInquiry_no());
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				inquiry.setContent(rs.getString("content"));
				inquiry.setTitle(rs.getString("title"));
				inquiry.setWrite_date(rs.getDate("write_date"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return inquiry;
	}
	
	@Override
	public InquiryAnswer selectInquiryAnswer(Connection conn, InquiryAnswer inquiryAnswer) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT * FROM inquiry_answer ";
		sql += "WHERE inquiry_no=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, inquiryAnswer.getInquiry_no());
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				inquiryAnswer.setContent(rs.getString("content"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return inquiryAnswer;
	}
	
	@Override
	public void deleteInquiry(Connection conn, Inquiry inquiry) {
		PreparedStatement ps = null;
		int res = 0;
		
		String sql = "";
		sql += "DELETE inquiry ";
		sql += "WHERE inquiry_no=?";
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, inquiry.getInquiry_no());
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
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
	public void deleteInquiryAnswer(Connection conn, Inquiry inquiry) {
		PreparedStatement ps = null;
		int res = 0;
		
		String sql = "";
		sql += "DELETE inquiry_answer ";
		sql += "WHERE inquiry_no=?";
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, inquiry.getInquiry_no());
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
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

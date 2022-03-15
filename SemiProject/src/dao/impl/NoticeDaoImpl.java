package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.NoticeDao;
import dto.Notice;
import util.Paging;

public class NoticeDaoImpl implements NoticeDao{
	
	@Override
	public List<Notice> getNoticeList(Connection conn, Paging paging) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, n.* FROM (";
		sql += " 		SELECT * FROM notice";
		sql += "		ORDER BY notice_no DESC";
		sql += " 	) n";
		sql += " ) notice";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Notice> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Notice notice = new Notice();
				notice.setNotice_no(rs.getInt("notice_no"));
				notice.setTitle(rs.getString("title"));
				notice.setWrite_date(rs.getDate("write_date"));
				
				list.add(notice);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
	
		return list;
	}
	
	@Override
	public int selectCntAll(Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT count(*) cnt FROM notice";
		
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
	public Notice getNoticeList(Connection conn, Notice notice) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Notice n = new Notice();
		
		String sql = "";
		sql += "SELECT * FROM notice ";
		sql += "WHERE notice_no=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, notice.getNotice_no());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				n.setNotice_no(rs.getInt("notice_no"));
				n.setContent(rs.getString("content"));
				n.setTitle(rs.getString("title"));
				n.setWrite_date(rs.getDate("write_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return n;
	}
}

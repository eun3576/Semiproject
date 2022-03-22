package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ManagerNoticeDao;
import dto.ManagerNotice;
import util.Paging;

public class ManagerNoticeDaoImpl implements ManagerNoticeDao {

	private PreparedStatement ps = null; //SQL수행 객체 
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public List<ManagerNotice> selectAll(Connection conn) {
		
		//SQL 작성
		String sql ="";
		sql += "SELECT";
		sql += "	notice_no";
		sql += "	, title";
//		sql += "	, content";
		sql += "	, write_date";
		sql += "    , manager_no";
		sql += " FROM notice";
		sql += " ORDER BY noticeno DESC";
		
		//결과 저장할 List
		List<ManagerNotice> noticeList = new ArrayList<>();  
		
		try {
			ps = conn.prepareStatement(sql); 
			
			rs = ps.executeQuery(); 
			
			while( rs.next() ) {
				ManagerNotice n = new ManagerNotice(); 
				
				n.setNoticeNo( rs.getInt("notice_no") );
				n.setTitle( rs.getString("title") );
//				n.setContent( rs.getString("content") );
				n.setWriteDate( rs.getDate("write_date") );
				n.setManagerNo( rs.getInt("manager_no") );
				
				noticeList.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return noticeList;
	}
	
	@Override
	public List<ManagerNotice> selectAll(Connection conn, Paging paging) {

		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, N.* FROM (";
		sql += " 		SELECT";
		sql += "			notice_no, title, write_date, manager_no";
		sql += "		FROM notice";
		sql += "		ORDER BY notice_no DESC";
		sql += " 	) N";
		sql += " ) NOTICE";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<ManagerNotice> noticeList = new ArrayList<>();  
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				ManagerNotice n = new ManagerNotice();
				
				//결과 값 한 행 처리
				n.setNoticeNo( rs.getInt("notice_no") );
				n.setTitle( rs.getString("title") );
//				n.setContent( rs.getString("content") );
				n.setWriteDate( rs.getDate("write_date") );
				n.setManagerNo( rs.getInt("manager_no") );
				
				noticeList.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return noticeList;
	}


	@Override
	public int selectCntAll(Connection conn) {
		
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
		
		return count ;
	}
	
	
	@Override
	public ManagerNotice selectNoticeByNoticeNo(Connection conn, String noticeNo) {
			
		ResultSet rs = null;
		PreparedStatement ps = null;
		
			String sql ="";
			sql += "SELECT";
			sql += " notice_no ";
			sql += "	, title ";
			sql += "	, content ";
			sql += "	, write_date ";
			sql += "    , manager_no ";
			sql += " FROM notice ";
			sql += " WHERE notice_No = ? ";
			
			ManagerNotice notice = new ManagerNotice();
			
			try {
				ps = conn.prepareStatement(sql);
				
				ps.setInt(1, Integer.parseInt(noticeNo));
				
				rs = ps.executeQuery();
				
				while( rs.next() ) {
									
					notice.setNoticeNo( rs.getInt("notice_no") );
					notice.setTitle( rs.getString("title") );
					notice.setContent( rs.getString("content") );
					notice.setWriteDate( rs.getDate("write_date") );
					notice.setManagerNo( rs.getInt("manager_no") );
				
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
			
			return notice;
	}
	
	@Override
	public int insert(Connection conn, ManagerNotice notice) {
		
		String sql = "";
		sql += "INSERT INTO notice(NOTICE_NO, TITLE, CONTENT, MANAGER_NO)";
		sql += "VALUES (notice_seq.nextval, ?, ?, ?)";
		
		int res = 0;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, notice.getTitle());
			ps.setString(2, notice.getContent());
			ps.setInt(3, notice.getManagerNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

//삭제
	
	@Override
	public int delete(Connection conn, ManagerNotice notice) {
		
		String sql = "";
		sql += "DELETE notice";
		sql += " WHERE notice_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, notice.getNoticeNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}


//수정
	@Override
	public int update(Connection conn, ManagerNotice notice) {

		String sql = "";
		sql += "UPDATE NOTICE";
		sql += " SET TITLE = ?,";
		sql += " 	CONTENT = ?";
		sql += " WHERE NOTICE_NO = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, notice.getTitle());
			ps.setString(2, notice.getContent());
			ps.setInt(3, notice.getNoticeNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	//검색창 만들기
	@Override
	public List<ManagerNotice> selectAll(Connection conn, Paging paging, String search) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, N.* FROM (";
		sql += " 		SELECT";
		sql += "			notice_no, title, write_date, manager_no";
		sql += "		FROM notice";
		sql += "        WHERE TITLE LIKE ?  ";
		sql += "		ORDER BY notice_no DESC";
		sql += " 	) N";
		sql += " ) NOTICE";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		
		List<ManagerNotice> noticeList = new ArrayList<>();  
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, "%" + search + "%");
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				ManagerNotice n = new ManagerNotice();
				
				n.setNoticeNo( rs.getInt("notice_no") );
				n.setTitle( rs.getString("title") );
//				n.setContent( rs.getString("content") );
				n.setWriteDate( rs.getDate("write_date") );
				n.setManagerNo( rs.getInt("manager_no") );
				
				noticeList.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return noticeList;
	}
}

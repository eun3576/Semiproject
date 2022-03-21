package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.InquiryAnswerDao;
import dto.ManagerInquiryAnswer;
import dto.ManagerNotice;
import util.Paging;

public class InquiryAnswerDaoImpl implements InquiryAnswerDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public List<ManagerInquiryAnswer> selectAll(Connection conn) {
		
		//SQL작성
		String sql = "";
		sql += " SELECT ";
		sql += "	ANSWER_NO ";
		sql += "	,CONTENT";
		sql += "	,WRITE_DATE ";	
		sql += "	,MANAGER_NO ";	
		sql += "	,INQUIRY_NO ";
		sql += " FROM INQUIRY_ANSWER ";
		sql += " ORDER BY ANSWER_NO DESC ";
		
		//결과 저장할 List
		List<ManagerInquiryAnswer> inquiryAnswerList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				ManagerInquiryAnswer i = new ManagerInquiryAnswer();
				
				i.setAnswerNo( rs.getInt("answer_no") );
				i.setContent( rs.getString("content") );
				i.setWriteDate( rs.getDate("write_date") );
				i.setManagerNo( rs.getInt("manager_no") );
				i.setInquiryNo( rs.getInt("inquiry_no") );
				
				inquiryAnswerList.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return inquiryAnswerList;
	}

	@Override
	public List<ManagerInquiryAnswer> selectAll(Connection conn, Paging paging) {

		String sql = "";
		sql += " SELECT * FROM ( ";
		sql += " 		SELECT rownum rnum, I. * FROM( ";
		sql += " 	SELECT ANSWER_NO, CONTENT, WRITE_DATE, MANAGER_NO, INQUIRY_NO FROM INQUIRY_ANSWER ";
		sql += " 		ORDER BY ANSWER_NO DESC) I ";
		sql += " 		) INQUIRY_ANSWER ";
		sql += " 	WHERE rnum BETWEEN ? AND ? ";
		
		//결과 저장할 List
		List<ManagerInquiryAnswer> inquiryAnswerList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				ManagerInquiryAnswer i = new ManagerInquiryAnswer();
				
				i.setAnswerNo( rs.getInt("answer_no") );
				i.setContent( rs.getString("content") );
				i.setWriteDate( rs.getDate("write_date") );
				i.setManagerNo( rs.getInt("manager_no") );
				i.setInquiryNo( rs.getInt("inquiry_no") );
				
				inquiryAnswerList.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return inquiryAnswerList;
	}
	
	@Override
	public int selectCntAll(Connection conn) {
		
				String sql = "";
				sql += "SELECT count(*) cnt FROM INQUIRY_ANSWER";
				
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
	public ManagerInquiryAnswer selectInquiryAnswerByInquiryNo(Connection conn, String inquiryNo) {
			
		ResultSet rs = null;
		PreparedStatement ps = null;
		
			//SQL 작성
			String sql ="";
			sql += "SELECT";
			sql += " ANSWER_NO ";
			sql += "	, CONTENT ";
			sql += "	, WRITE_DATE ";
			sql += "	, MANAGER_NO ";
			sql += "    , INQUIRY_NO ";
			sql += " FROM INQUIRY_ANSWER ";
			sql += " WHERE INQUIRY_NO = ? ";
			
			ManagerInquiryAnswer inquiryAnswer = new ManagerInquiryAnswer();
			
			try {
				ps = conn.prepareStatement(sql); 
				
				ps.setInt(1, Integer.parseInt(inquiryNo));
				
				rs = ps.executeQuery(); 
				
				while( rs.next() ) {
								
					inquiryAnswer = new ManagerInquiryAnswer();
					
					inquiryAnswer.setAnswerNo( rs.getInt("answer_no") );
					inquiryAnswer.setContent( rs.getString("content") );
					inquiryAnswer.setWriteDate( rs.getDate("write_date") );
					inquiryAnswer.setManagerNo( rs.getInt("manager_no") );
					inquiryAnswer.setInquiryNo( rs.getInt("inquiry_no") );
				
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
	public int insert(Connection conn, ManagerInquiryAnswer inquiryAnswer) {
		
		String sql = "";
		sql += "INSERT INTO INQUIRY_ANSWER(ANSWER_NO, CONTENT, MANAGER_NO, INQUIRY_NO)";
		sql += "VALUES (INQUIRY_ANSWER_seq.nextval, ?, ?, ?)";
		
		int res = 0;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, inquiryAnswer.getContent());
			ps.setInt(2, inquiryAnswer.getManagerNo());
			ps.setInt(3, inquiryAnswer.getInquiryNo());
			
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
	public int delete(Connection conn, ManagerInquiryAnswer inquiryAnswer) {
		
		String sql = "";
		sql += "DELETE INQUIRY_ANSWER";
		sql += " WHERE INQUIRY_NO = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, inquiryAnswer.getInquiryNo());

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
	public int update(Connection conn, ManagerInquiryAnswer inquiryAnswer) {

		String sql = "";
		sql += "UPDATE INQUIRY_ANSWER";
		sql += " SET CONTENT = ?";
		sql += " WHERE INQUIRY_NO = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, inquiryAnswer.getContent());
			ps.setInt(2, inquiryAnswer.getInquiryNo());

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
	public List<ManagerInquiryAnswer> selectAll(Connection conn, Paging paging, String search) {

		String sql = "";
		sql += " SELECT * FROM ( ";
		sql += " 		SELECT rownum rnum, I. * FROM( ";
		sql += " 	SELECT ANSWER_NO, CONTENT, WRITE_DATE, MANAGER_NO, INQUIRY_NO FROM INQUIRY_ANSWER ";
		sql += "        WHERE CONTENT LIKE ?  ";
		sql += " 		ORDER BY INQUIRY_NO DESC) I ";
		sql += " 		) INQUIRY_ANSWER ";
		sql += " 	WHERE rnum BETWEEN ? AND ? ";
		
		//결과 저장할 List
		List<ManagerInquiryAnswer> inquiryAnswerList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, "%" + search + "%");
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				ManagerInquiryAnswer i = new ManagerInquiryAnswer();
				
				i.setAnswerNo( rs.getInt("answer_no") );
				i.setContent( rs.getString("content") );
				i.setWriteDate( rs.getDate("write_date") );
				i.setManagerNo( rs.getInt("manager_no") );
				i.setInquiryNo( rs.getInt("inquiry_no") );
				
				inquiryAnswerList.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return inquiryAnswerList;
	}
}

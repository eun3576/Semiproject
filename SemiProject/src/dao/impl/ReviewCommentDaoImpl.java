package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ReviewCommentDao;
import dto.ManagerReviewComment;
import util.Paging;

public class ReviewCommentDaoImpl implements ReviewCommentDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL 조회 결과 객체
	
	@Override
	public List<ManagerReviewComment> selectAll(Connection conn) {
		
		//SQL작성
		String sql = "";
		sql += " SELECT ";
		sql += "	COMMENT_NO ";
		sql += "	, USER_NO ";
		sql += "	, COMMENT_TEXT ";
		sql += "	, COMMENT_DATE ";
		sql += "	, COMMENT_UPDATE ";
		sql += "	, REVIEW_NO ";
		sql += " FROM REVIEW_COMMENT ";
		sql += " ORDER BY COMMENT_NO DESC ";
		
		//결과 저장할 List
		
		List<ManagerReviewComment> reviewCommentList = new ArrayList<>();
		
		try {
		
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			
			while( rs.next() ) {
				ManagerReviewComment r = new ManagerReviewComment();
					
			r.setCommentNo( rs.getInt("COMMENT_NO") );
			r.setUserNo( rs.getInt("USER_NO") );
			r.setCommentText( rs.getString("COMMENT_TEXT") );
			r.setCommentDate( rs.getDate("COMMENT_DATE") );
			r.setCommentUpdate( rs.getDate("COMMENT_UPDATE") );
			r.setReviewNo( rs.getInt("REVIEW_NO") );
		
			reviewCommentList.add(r);
		}
			
			for(int i=0;i<reviewCommentList.size(); i++) {
				System.out.println("@@@" + reviewCommentList.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return reviewCommentList;
	}

	@Override
	public List<ManagerReviewComment> selectAll(Connection conn, Paging paging) {
		
		//SQL 작성
				String sql = "";
				sql += "SELECT * FROM (";
				sql += "	SELECT rownum rnum, R.* FROM (";
				sql += " 		SELECT COMMENT_NO, USER_NO, COMMENT_TEXT, COMMENT_DATE, COMMENT_UPDATE, REVIEW_NO";
				sql += "		FROM REVIEW_COMMENT";
				sql += "		ORDER BY COMMENT_NO DESC) R";
				sql += " ) REVIEW_COMMENT";
				sql += " WHERE rnum BETWEEN ? AND ?";
				
				//결과 저장할 List
				List<ManagerReviewComment> reviewCommentList = new ArrayList<>();
				
				try {
					ps = conn.prepareStatement(sql); //SQL수행 객체
					
					ps.setInt(1, paging.getStartNo());
					ps.setInt(2, paging.getEndNo());
					
					rs = ps.executeQuery(); //SQL수행 및 결과집합 저장
					
					while( rs.next() ) {
						ManagerReviewComment r = new ManagerReviewComment(); //결과값 저장 객체
						
						//결과값 한 행 처리
						r.setCommentNo( rs.getInt("COMMENT_NO") );
						r.setUserNo( rs.getInt("USER_NO") );
						r.setCommentText( rs.getString("COMMENT_TEXT") );
						r.setCommentDate( rs.getDate("COMMENT_DATE") );
						r.setCommentUpdate( rs.getDate("COMMENT_UPDATE") );
						r.setReviewNo( rs.getInt("REVIEW_NO") );
						
						//리스트객체에 조회한 행 객체 저장
						reviewCommentList.add(r);
					}
					for(int i=0;i<reviewCommentList.size(); i++) {
						System.out.println("@@@" + reviewCommentList.get(i));
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					//JDBC객체 닫기
					JDBCTemplate.close(rs);
					JDBCTemplate.close(ps);
				}
				
				//최종 조회 결과 반환
				return reviewCommentList;
	}

	@Override
	public int selectCntAll(Connection conn) {
		
		//SQL 작성
				String sql = "";
				sql += "SELECT count(*) cnt FROM REVIEW_COMMENT";
				
				//총 게시글 수
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
	public int delete(Connection conn, ManagerReviewComment reviewComment) {
		
		String sql = "";
		sql += "DELETE REVIEW_COMMENT";
		sql += " WHERE COMMENT_NO = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewComment.getCommentNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	// 재렬
	@Override
	public List<ManagerReviewComment> selectAll(Connection conn, Paging paging, String search) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, R.* FROM (";
		sql += " 		SELECT COMMENT_NO, USER_NO, COMMENT_TEXT, COMMENT_DATE, COMMENT_UPDATE, REVIEW_NO";
		sql += "		FROM REVIEW_COMMENT";
		sql += "        WHERE COMMENT_TEXT LIKE ?  ";
		sql += "		ORDER BY COMMENT_NO DESC) R";
		sql += " ) REVIEW_COMMENT";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<ManagerReviewComment> reviewCommentList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, "%" + search + "%");
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			
			rs = ps.executeQuery(); 
			
			while( rs.next() ) {
				ManagerReviewComment r = new ManagerReviewComment();
				
				r.setCommentNo( rs.getInt("COMMENT_NO") );
				r.setUserNo( rs.getInt("USER_NO") );
				r.setCommentText( rs.getString("COMMENT_TEXT") );
				r.setCommentDate( rs.getDate("COMMENT_DATE") );
				r.setCommentUpdate( rs.getDate("COMMENT_UPDATE") );
				r.setReviewNo( rs.getInt("REVIEW_NO") );
				
				reviewCommentList.add(r);
			}
			for(int i=0;i<reviewCommentList.size(); i++) {
				System.out.println("@@@" + reviewCommentList.get(i));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return reviewCommentList;
	}
	
}



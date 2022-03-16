package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ReviewDao;
import dto.Review;

public class ReviewDaoImpl implements ReviewDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<Review> selectAll(Connection conn) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += " review_no";
		sql += ", title";
		sql += ", content";
		sql += ", views";
		sql += ", write_date";
		sql += ", update_date";
		sql += ", user_no";
		sql += " FROM review";
		sql += " ORDER BY review_no DESC";
		
		//결과 저장할 List
		List<Review> reviewList = new ArrayList<>();
		
		try {
			//SQL 수행 객체
			ps = conn.prepareStatement(sql);
			
			//SQL 수해 및 결과 저장
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				//결과 값 저장 객체
				Review r = new Review();
				
				//결과 값 한 행 처리 
				r.setReview_no(rs.getInt("review_no"));
				r.setTitle(rs.getString("title"));
				r.setContent(rs.getString("content"));
				r.setWriteDate(rs.getDate("write_date"));
				r.setUpdateDate(rs.getDate("update_date"));
				r.setViews(rs.getInt("views"));
				r.setUser_no(rs.getInt("user_no"));
				
				//리스트 객체에 조회한 행 객체 저장
				reviewList.add(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//객체 닫기
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return reviewList;
	}

	@Override
	public Review selectReviewByReviewno(Connection conn, Review reviewno) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += " review_no";
		sql += ", title";
		sql += ", content";
		sql += ", views";
		sql += ", write_date";
		sql += ", update_date";
		sql += ", user_no";
		sql += " FROM review";
		sql += " WHERE review_no = ?";
		
		//결과 저장할 DTO객체
		Review review = null;
		
		try {
			//SQL수행 객체
			ps = conn.prepareStatement(sql);
			
			//SQL수행문 1번째 매개변수에 reviewno 대입
			ps.setInt(1, reviewno.getReview_no());
			
			//SQL수행 및 결과 집합 저장
			rs = ps.executeQuery();
			
			while (rs.next()) {
				//결과값 저장 객체
				review = new Review();
				
				//결과값 행 처리
				review.setReview_no(rs.getInt("review_no"));
				review.setTitle(rs.getString("title"));
				review.setContent(rs.getString("content"));
				review.setWriteDate(rs.getDate("write_date"));
				review.setUpdateDate(rs.getDate("update_date"));
				review.setViews(rs.getInt("views"));
				review.setUser_no(rs.getInt("user_no"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return review;
	}

	@Override
	public int updateView(Connection conn, Review reviewno) {
		
		String sql = "";
		sql += "UPDATE review";
		sql += " SET views = views + 1";
		sql += " WHERE review_no = ?";
		
		int res = 0;
		
		try {
			//SQL수행 
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, reviewno.getReview_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int insert(Connection conn, Review review) {
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "INSERT INTO review(user_no, review_no, title, content, views)";
		
		//아직 세션을 받아 로그인 기능을 추가를 하지않아 user_no 4로 고정
		
		sql += " VALUES (4, review_seq.nextval, ?, ?, 0)";
		
		int res = 0;
		
		try {
			//폼데이터 에서 입력한 데이터를 매개변수로 받고 
			//매개변수로 받은 데이터를 DTO set 하여 DB객체에 get을 통하여 
			//sql 쿼리문에 대입
			ps = conn.prepareStatement(sql);
			ps.setString(1, review.getTitle());
			ps.setString(2, review.getContent());
			
			//삽입 쿼리문의 결과~
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int update(Connection conn, Review review) {
		
		//SQL 구문
		String sql = "";
		sql += "UPDATE review";
		sql += " SET title = ?,";
		sql += " content = ?";
		sql += " WHERE review_no = ?";
		
		//쿼리문 수행 결과 저장 변수
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, review.getTitle());
			ps.setString(2, review.getContent());
			ps.setInt(3, review.getReview_no());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}
		
		System.out.println(review);
		
		System.out.println("res 값? " + res);
		
		return res;
		
	}

	@Override
	public int delete(Connection conn, Review review) {
		
		String sql = "";
		sql += "DELETE review";
		sql += " WHERE review_no = ?";
		
		int res = -1;
		
		try {
			// SQL구문 실행
			ps = conn.prepareStatement(sql);
			ps.setInt(1, review.getReview_no());
			
			//쿼리문 실행 결과 
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return res;
	}
}

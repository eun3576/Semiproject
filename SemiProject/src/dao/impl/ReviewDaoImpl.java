package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ReviewDao;
import dto.Attachment;
import dto.Review;
import dto.UserInfo;

public class ReviewDaoImpl implements ReviewDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<Review> selectReviewAll(Connection conn) {
		
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
			
			//SQL 수행 및 결과 저장
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
	public List<UserInfo> selectNickSympAll(Connection conn) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += " nickname";
		sql	+= ", sympton";
		sql += " FROM review, userinfo";
		sql += " WHERE review.user_no = userinfo.user_no";
		sql += " ORDER BY review_no DESC";
		
		//유저 닉네임 리스트 생성
		List<UserInfo> nickList = new ArrayList<>();
		
		try {
			//SQL 수행 
			ps = conn.prepareStatement(sql);
			//SQL 수행 결과 저장 
			rs = ps.executeQuery();
			
			
			//결과 값 한 행 처리 
			while (rs.next()) {
				
				//결과 값 저장 객체
				UserInfo u = new UserInfo();
				
				u.setNickname(rs.getString("nickname"));
				u.setSympton(rs.getString("sympton"));
				
				//리스트 객체에 조회한 행 객체 저장
				nickList.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return nickList;
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
	public UserInfo selectNickSympByReviewno(Connection conn, Review reviewno) {
		
		//review_no에 따른 SQL 작성
		String sql = "";
		sql += "SELECT nickname, sympton"; 
		sql += " FROM review, userinfo";
		sql += " WHERE review.user_no = userinfo.user_no";
		sql += " AND review.review_no = ?";
		
		//유저 정보 저장할 객체
		UserInfo userInfo = null;
		
		try {
			
			//SQL 수행
			ps = conn.prepareStatement(sql);
			
			//SQL수행문 1번째 매개변수에 review_no 대입
			ps.setInt(1, reviewno.getReview_no());
			
			//SQL수행 및 결과 집합 저장
			rs = ps.executeQuery();
			
			while (rs.next()) {
				//유저 정보 저장할 객체
				userInfo = new UserInfo();
				
				//결과값 행 처리
				userInfo.setNickname(rs.getString("nickname"));
				userInfo.setSympton(rs.getString("sympton"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return userInfo;
	}

	@Override
	public int selectReivewno(Connection conn) {
		
		//SQL작성
		String sql = "";
		sql += "SELECT review_seq.nextval FROM dual";
		
		//다음 시퀀스 번호
		int nextReviewno = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL 수행 객체 
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회결과 처리
			while (rs.next()) {
				nextReviewno = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return nextReviewno;
	}
	
	@Override
	public UserInfo selectUsernobyNick(Connection conn, UserInfo userInfo) {
		
		String sql = "";
		sql += "SELECT user_no FROM userinfo WHERE nickname = ?";
		
		
		try {
			//SQL 수행
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, userInfo.getNickname());
			
			rs = ps.executeQuery();
			
			//조회결과 처리 
			while (rs.next()) {
				userInfo.setUserNo(rs.getInt("user_no"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userInfo;
	}
		
	
	
	@Override
	public int insertFile(Connection conn, Attachment attach) {
		
		String sql = "";
		sql += "INSERT INTO attachment(attachment_no, stored_img, origin_img, review_no, filesize)";
		sql += " VALUES (attachment_seq.nextval, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, attach.getStored_img());
			ps.setString(2, attach.getOrigin_img());
			ps.setInt(3, attach.getReview_no());
			ps.setInt(4, attach.getFilesize());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	
	@Override
	public Attachment selectFile(Connection conn, Review reviewno) {
		
		//SQL작성
		String sql = "";
		sql += "SELECT";
		sql += "	attachment_no";
		sql += "	, stored_img";
		sql += "	, origin_img";
		sql += "	, review_no";
		sql += "	, filesize";
		sql += " FROM attachment";
		sql += " WHERE review_no = ?";
		sql += " ORDER BY attachment_no DESC";
		
		//결과 저장할 DTO 객체 
		Attachment attach = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, reviewno.getReview_no());
			
			rs = ps.executeQuery();
			
//			while (rs.next()) {
			if (rs.next()) {
				
				attach = new Attachment();
				
				//결과값 행 처리 
				attach.setAttachment_no(rs.getInt("attachment_no"));
				attach.setStored_img(rs.getString("stored_img"));
				attach.setOrigin_img(rs.getString("origin_img"));
				attach.setReview_no(rs.getInt("review_no"));
				attach.setFilesize(rs.getInt("filesize"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return attach;
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
		sql += "INSERT INTO review(review_no, user_no, title, content, views)";
		sql += " VALUES (?, ?, ?, ?, 0)";
		
		int res = 0;
		
		try {
			//폼데이터 에서 입력한 데이터를 매개변수로 받고 
			//매개변수로 받은 데이터를 DTO set 하여 DB객체에 get을 통하여 
			//sql 쿼리문에 대입
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, review.getReview_no());
			ps.setInt(2, review.getUser_no());
			ps.setString(3, review.getTitle());
			ps.setString(4, review.getContent());
			
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
		sql += " content = ?,";
		sql += " update_date = sysdate";
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


	@Override
	public int deleteFile(Connection conn, Review review) {
		
		String sql = "";
		sql += "DELETE attachment"
				+ " WHERE review_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, review.getReview_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		
		return res;
	}


}

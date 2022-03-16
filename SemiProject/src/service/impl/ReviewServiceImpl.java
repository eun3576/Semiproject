package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.ReviewDao;
import dao.impl.ReviewDaoImpl;
import dto.Review;
import service.face.ReviewService;

public class ReviewServiceImpl implements ReviewService{

	private ReviewDao reviewDao = new ReviewDaoImpl();
	
	@Override
	public List<Review> getList() {
		
		//게시글 전체 조회 결과 반환
		return reviewDao.selectAll(JDBCTemplate.getConnection());
	}

	@Override
	public Review getReviewno(HttpServletRequest req) {
		
		//전달 파라미터 review_no를 저장할 DTO 객체 생성
		Review reviewno = new Review();
		
		String param = req.getParameter("review_no");
		if (param != null && !"".equals(param)) {
			reviewno.setReview_no(Integer.parseInt(param));
		} else {
			System.out.println("[WARN] ReviewService getReviewno() - reviewno값이 비어있거나 null 이다!!");
		}
		
		return reviewno;
	}

	@Override
	public Review view(Review reviewno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//조회수 증가
		if (reviewDao.updateView(conn, reviewno) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//게시글 조회
		Review review = reviewDao.selectReviewByReviewno(conn, reviewno);
		
		return review;
	}

	@Override
	public void write(HttpServletRequest req) {
		
		Review review = new Review();
		
		review.setTitle(req.getParameter("reviewTitle"));
		review.setContent(req.getParameter("reviewContent"));
		
//		review.setUser_no( (String) req.getSession().getAttribute("유저 정보"));
		
		//작성자가 작성글 제목에 아무것도 입력하지 않았을 때 제목없음 추가 된다.
		if (review.getTitle() == null || "".equals(review.getTitle())) {
			review.setTitle("(제목없음)");
		}
		
		Connection conn = JDBCTemplate.getConnection();
		
		if (reviewDao.insert(conn, review) > 0) {
			
			//insert를 통한 쿼리문이 정상적으로 실행되면 commit
			JDBCTemplate.commit(conn);
		} else {
			//insert를 통한 쿼리문이 에러가 나면 rollback
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public void update(HttpServletRequest req) {
		
		//후기글 정보 DTO객체
		Review review = new Review();
		
		System.out.println("set전 review 객체 + " + review);
		
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		System.out.println(req.getParameter("review_no"));
		//수정 페이지에서 입력한 폼 데이터 파라미터 불러와서 DTO 객체에 저장
		review.setTitle(req.getParameter("reviewTitle"));
		review.setContent(req.getParameter("reviewContent"));
		
		//후기글 정보 수정
		if(review.getTitle() == null || "".equals(review.getTitle())) {
			review.setTitle("(제목없음)");
		}
		
		if(reviewDao.update(conn, review) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		System.out.println("set과 reviewDao.update 후 review 객체 : " + review);
	}

	@Override
	public void delete(Review review) {
		
		Connection conn = JDBCTemplate.getConnection();

		if( reviewDao.delete(conn, review) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}

}

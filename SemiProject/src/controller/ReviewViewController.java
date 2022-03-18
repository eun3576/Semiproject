package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Attachment;
import dto.Review;
import dto.ReviewComment;
import dto.UserInfo;
import service.face.ReviewService;
import service.impl.ReviewServiceImpl;

// 제품 후기 자세히 보기로 이동하는 컨트롤러 + 조회수 증가
@WebServlet("/review/view")
public class ReviewViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/review/view [GET]");
		
		//전달 파라미터 얻기 - reviewno
		Review reviewno = reviewService.getReviewno(req);
		
		//요청에 따른 reviewno 파라미터 얻었는지 test
		//System.out.println(reviewno);
		
		
		//상세보기 결과 조회
		Review viewReview = reviewService.view(reviewno);
		
		//reviewno에 따른 유저정보 객체 
		UserInfo userInfo = reviewService.getNickSymptonByReviewno(reviewno);
		
		//첨부파일 정보 조회
		Attachment attach = reviewService.viewFile(viewReview);
		
		//댓글 정보 리스트 조회
		List<ReviewComment> reviewComment = reviewService.getCommentList(reviewno);
		
		
		//viewReview, userInfo 중간 test
		//System.out.println("ReviewController viewReview - " + viewReview);
		//System.out.println("ReviewController userInfo" + userInfo);
		
		//list reviewComment 댓글 정보 불러와졌는지 테스트
		//for (int i = 0; i < reviewComment.size(); i++) {
		//	System.out.println(reviewComment.get(i));
		//}
		
		
		//조회결과 MODEL값 전달
		req.setAttribute("viewReview", viewReview);
		//유저 정보 조회 결과 MODEL값 전달
		req.setAttribute("userInfo", userInfo);
		//댓글 정보 조회 결과 MODEL값 전달
		req.setAttribute("reviewComment", reviewComment);
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("attach", attach);
		
		//VIEW지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/review/view.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/review/view [POST]");
		
		req.setCharacterEncoding("UTF-8");
		
		//view.jsp에서 입력한 폼데이터(댓글) 저장하는 method
		reviewService.writeComment(req);
		
		resp.sendRedirect("#");
	}
}

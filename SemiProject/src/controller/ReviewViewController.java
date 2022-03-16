package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Review;
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
		System.out.println(reviewno);
		
		//상세보기 결과 조회
		Review viewReview = reviewService.view(reviewno);
		
		//reviewno에 따른 유저정보 객체 
		UserInfo userInfo = reviewService.getUserInfoByReviewno(reviewno);
		
		
		//viewReview, userinfo 중간 test
//		System.out.println("ReviewController viewReview - " + viewReview);
		System.out.println("ReviewController userinfo" + userInfo);
		
		//조회결과 MODEL값 전달
		req.setAttribute("viewReview", viewReview);
		req.setAttribute("userInfo", userInfo);
		
		//VIEW지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/review/view.jsp").forward(req, resp);
		
	}
}

package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Attachment;
import dto.Review;
import dto.UserInfo;
import service.face.ReviewService;
import service.impl.ReviewServiceImpl;

@WebServlet("/review/update")
public class ReviewUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/review/update [GET]");
		
		//선택한 게시물의 전달 파라미터 얻기
		Review reviewno = reviewService.getReviewno(req);
		
		//상세보기 결과 조회
		Review updateReview = reviewService.view(reviewno);
		
		//조회결과 MODEL값 전달
		req.setAttribute("updateReview", updateReview);
		
		//review_no통하여 nick, symptom 조회
		UserInfo userInfo = reviewService.getNickSymptonByReviewno(reviewno);
		
		
		Attachment updateAttach = reviewService.viewFile(reviewno);
		
		
		//객체에 데이터가 들어갔는지 TEST!
		System.out.println("객체에 데이터가 들어 갔는지 테스트!"
				+ "ReviewController viewReview - " + updateReview);
		System.out.println("ReviewController updateAttach - " + updateAttach);
		System.out.println("ReviewController userInfo - " + userInfo);
		
		//조회결과 MODEL값 전달
		req.setAttribute("userInfo", userInfo);
		
		//조회결과 MODER값 전달
		req.setAttribute("updateAttach", updateAttach);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/review/update.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/review/update [POST]");
		
		
		//한글 수정글 인코딩
		req.setCharacterEncoding("UTF-8");

		//게시글 수정 시 update method review객체가 반영이 안된다.
		reviewService.update(req);
		
		
		resp.sendRedirect("/review/list");
	}
}

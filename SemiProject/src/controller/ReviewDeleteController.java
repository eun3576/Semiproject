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

@WebServlet("/review/delete")
public class ReviewDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/review/delete [GET]");
		
		//전달 파라미터 review_no가 저장된 DTO를 reviewno에 저장
		Review reviewno = reviewService.getReviewno(req);
		
		//review_no가 저장된 DTO객체를 이용하여 일치하는 유저의 닉네임과 증상 구하기
		UserInfo userInfo = reviewService.getNickSymptonByReviewno(reviewno);
		
		
		
		//세션의 닉네임과 게시글의 닉네임 정보의 일치에 따라 삭제 가능하게 하기
		String sessionNick = (String) req.getSession().getAttribute("usernick");
		//세션의 닉네임 불러오기 테스트
		System.out.println("세션 닉네임 : " + sessionNick);
		
		if ("".equals(sessionNick) || sessionNick == null) {
			
			System.out.println("로그인이 필요 합니다!");
			resp.sendRedirect("/");
			
		} else if (sessionNick.equals(userInfo.getNickname())) {
			
			System.out.println("로그인 닉네임과 게시글의 닉네임이 일치합니다!");
			
			//review_no가 저장된 DTO객체를 이용하여 삭제
			reviewService.delete(reviewno);
			
			//목록으로 리다이렉트
			resp.sendRedirect("/review/list");
			
		} else {
			
			System.out.println("본인이 작성한 글만 삭제 가능 합니다!");
			
			//목록으로 리다이렉트
			resp.sendRedirect("/review/list");
			
		}
		
		//에러 SLQ더미 데이터로 생성한 게시글은 삭제가 안되지만 client로 생성한 글은 삭제된다.
	}
}

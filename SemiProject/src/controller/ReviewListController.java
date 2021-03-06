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
import dto.UserInfo;
import service.face.ReviewService;
import service.impl.ReviewServiceImpl;

// 제품 후기 리스트 조회하는 컨트롤러

@WebServlet("/review/list")
public class ReviewListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewService reviewService = new ReviewServiceImpl(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/review/list [GET]");
		
		//후기글 전체 조회 - ReviewService이용
		List<Review> reviewList = reviewService.getList();
		
		//후기글 전체 조회 test
//		System.out.println("게시글 전체 조회" + reviewList);
		
		
		//후기글을 작성한 유저 닉네임 증상 전체 조회 - ReviewService이용
		List<UserInfo> nickList = reviewService.getNickSympList();
		
		//후기글에 따른 유저 닉네임 증상 전체 조회 test
//		System.out.println("게시글 전체 조회" + nickList);
		
		//후기글에 따른 stored_img list - ReviewService 이용
		List<Attachment> attachList = reviewService.getAttachList();
		
		//후기글에 따른 이미지 리스트 test
//		System.out.println("후기글 이미지 리스트" + attachList);
		
		
		//조회결과 MODEL값 전달 - req.Attribute
		req.setAttribute("reviewList", reviewList);
		req.setAttribute("nickList", nickList);
		req.setAttribute("attachList", attachList);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/review/list.jsp").forward(req, resp);
	}
}

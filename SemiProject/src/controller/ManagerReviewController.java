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
import service.face.ManagerService;
import service.face.ReviewService;
import service.impl.ManagerServiceImpl;
import service.impl.ReviewServiceImpl;

@WebServlet(urlPatterns = "/manager/review")
public class ManagerReviewController extends HttpServlet{

	//서비스 객체
	private ManagerService managerSevice= new ManagerServiceImpl();
	private ReviewService reviewService = new ReviewServiceImpl();
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//후기글 전체 조회 - ReviewService이용
		List<Review> reviewList = reviewService.getList();
				
		//후기글을 작성한 유저 닉네임 증상 전체 조회 - ReviewService이용
		List<UserInfo> nickList = reviewService.getNickSympList();
		
		//후기글에 따른 stored_img list - ReviewService 이용
		List<Attachment> attachList = reviewService.getAttachList();
		
		String searchTitle = req.getParameter("searchTitle");
		
		if(searchTitle != null && searchTitle.length() > 0) {
			reviewList = reviewService.searchTitleList(searchTitle);
		}
				
		
		//조회결과 MODEL값 전달 - req.Attribute
		req.setAttribute("reviewList", reviewList);
		req.setAttribute("nickList", nickList);
		req.setAttribute("attachList", attachList);
		
		req.getRequestDispatcher("/WEB-INF/views/manager/review.jsp").forward(req, resp); // 로그인 폼 보여주기
	
	}

	
	
}

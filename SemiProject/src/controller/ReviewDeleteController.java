package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Review;
import service.face.ReviewService;
import service.impl.ReviewServiceImpl;

@WebServlet("/review/delete")
public class ReviewDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/review/delete [GET]");
		
		Review review = reviewService.getReviewno(req);
		
		reviewService.delete(review);
		
		//목록으로 리다이렉트
		resp.sendRedirect("/review/list");
	}

}

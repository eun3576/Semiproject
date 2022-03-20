package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ManagerReviewComment;
import service.face.ReviewCommentService;
import service.impl.ReviewCommentServiceImpl;

@WebServlet("/reviewcomment/delete")
public class ReviewCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewCommentService reviewCommentService = new ReviewCommentServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ManagerReviewComment reviewComment = new ManagerReviewComment();
//		reviewComment.setCommentNo((int)req.getAttribute("CommentNo"));
		reviewComment.setCommentNo(Integer.parseInt( req.getParameter("CommentNo") ) );
		reviewCommentService.delete(reviewComment);
		
		//목록으로 리다이렉트
		resp.sendRedirect("/reviewcomment/list");
	
	}
	
}

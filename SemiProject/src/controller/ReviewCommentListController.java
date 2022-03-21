package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ManagerReviewComment;
import service.face.ReviewCommentService;
import service.impl.ReviewCommentServiceImpl;
import util.Paging;

@WebServlet("/reviewcomment/list")
public class ReviewCommentListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewCommentService reviewCommentService = new ReviewCommentServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	//전달 파라미터에서 현재 페이징 객체 알아내기
	Paging paging = reviewCommentService.getPaging(req);
	System.out.println("[TEST] - " + paging);
	
	//리뷰댓글 페이징 목록 조회 - NoticeService이용
	List<ManagerReviewComment> reviewCommentList = reviewCommentService.getList( paging ); 
	
	// 재렬
	String search = req.getParameter("search");
	if(search != null && search.length() > 0) {
		reviewCommentList = reviewCommentService.getList( paging, search ); 
	}
	
	// 조회결과 MODEL값 전달 - req.setAttribute
	req.setAttribute("reviewCommentList", reviewCommentList);	
	
	req.setAttribute("paging", paging);
	
	// VIEW 지정 및 응답
	req.getRequestDispatcher("/WEB-INF/views/reviewComment/list.jsp").forward(req, resp);
	
	}
}

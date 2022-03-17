package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProductTag;
import dto.Review;
import service.face.MainService;
import service.impl.MainServiceImpl;


@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체
	private MainService mainService= new MainServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//베스트 목록 가져오기
		List<ProductTag> pList = mainService.getBestList();
		
		//조회수 높은 리뷰 가져오기 
		List<Review> rList = mainService.getReviewList();

		//조회된 결과 view로 전달
		req.setAttribute("productTag", pList);
		req.setAttribute("reviewList", rList);
		req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
	}
}

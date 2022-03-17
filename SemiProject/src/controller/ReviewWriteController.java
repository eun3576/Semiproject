package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.ReviewService;
import service.impl.ReviewServiceImpl;

@WebServlet("/review/write")
public class ReviewWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/review/write [GET]");
		
		//로그인 되어 있지 않으면 리다이렉트
		if(req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/");
			
			return;
		}
		
		req.getRequestDispatcher("/WEB-INF/views/review/write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/review/write [POST]");
		
		req.setCharacterEncoding("UTF-8");
		
		//write.jsp 페이지에서 입력한 폼데이터 삽입
		reviewService.write(req);
		
		//목록으로 리다이렉션
		resp.sendRedirect("/review/list");
		
	}
	
}

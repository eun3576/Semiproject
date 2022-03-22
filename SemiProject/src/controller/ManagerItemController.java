package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.ManagerService;
import service.face.ReviewService;
import service.impl.ManagerServiceImpl;
import service.impl.ReviewServiceImpl;

@WebServlet(urlPatterns = "/manager/item")
public class ManagerItemController extends HttpServlet{

	//서비스 객체
	private ManagerService managerSevice= new ManagerServiceImpl();
	private ReviewService reviewService = new ReviewServiceImpl();
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
						
		
		req.getRequestDispatcher("/WEB-INF/views/manager/item.jsp").forward(req, resp); // 로그인 폼 보여주기
	
	}

	
	
}

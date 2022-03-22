package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.ManagerService;
import service.impl.ManagerServiceImpl;


@WebServlet("/manager/delete")
public class ManagerReviewDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ManagerService managerService = new ManagerServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//review_no가 저장된 DTO객체를 이용하여 일치하는 유저의 닉네임과 증상 구하기
		managerService.reviewDelete(req);
		
		resp.sendRedirect("/manager/review");
	}
}

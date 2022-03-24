package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.ManagerService;
import service.impl.ManagerServiceImpl;

@WebServlet(urlPatterns = "/manager/itemDelete")
public class ManagerItemDeleteController extends HttpServlet{

	//서비스 객체
	private ManagerService managerService= new ManagerServiceImpl();
		
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//문의글, 문의글 답변 삭제
		managerService.productDelete(req);
		
		//문의글 목록페이지로 리다이렉트
		resp.sendRedirect("/manager/item");
	
	}


	
	
}

package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.InquiryService;
import service.impl.InquiryServiceImpl;

@WebServlet("/inquiry/write")
public class InquiryWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	InquiryService inquiryService = new InquiryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//문의글 작성 페이지로 이동
		req.getRequestDispatcher("/WEB-INF/views/customer_service/inquirywrite.jsp").forward(req, resp);
		
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//한글 인코딩 설정
		req.setCharacterEncoding("utf-8");
		
		//요청 정보를 이용하여 문의글 등록
		inquiryService.insertInquiry(req);
		
		//문의글 목록으로 리다이렉트
		resp.sendRedirect("/inquiry/list");
	}
	
}

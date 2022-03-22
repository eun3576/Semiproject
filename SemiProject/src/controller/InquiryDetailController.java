package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Inquiry;
import dto.InquiryAnswer;
import service.face.InquiryService;
import service.impl.InquiryServiceImpl;

@WebServlet("/inquiry/detail")
public class InquiryDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	InquiryService inquiryService = new InquiryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//문의글 DTO 생성
		Inquiry inquiry = new Inquiry();
		
		//문의글 답변 DTO 생성
		InquiryAnswer inquiryAnswer = new InquiryAnswer();
		
		//요청 정보를 이용하여 문의글 조회 후 DTO 반환
		inquiry = inquiryService.getInquiry(req);
		
		//요청 정보를 이용하여 문의글 답변 조회 후 DTO 반환
		inquiryAnswer = inquiryService.getInquiryAnswer(req);
		
		//문의글 전달
		req.setAttribute("inquiry", inquiry);
		
		//문의글 답변 전달
		req.setAttribute("inquiryAnswer", inquiryAnswer);
		
		req.getRequestDispatcher("/WEB-INF/views/customer_service/inquirydetail.jsp").forward(req, resp);
	}
	
}

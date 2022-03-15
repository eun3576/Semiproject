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
		Inquiry inquiry = new Inquiry();
		InquiryAnswer inquiryAnswer = new InquiryAnswer();
		
		inquiry = inquiryService.getInquiry(req);
		inquiryAnswer = inquiryService.getInquiryAnswer(req);
		
		req.setAttribute("inquiry", inquiry);
		req.setAttribute("inquiryAnswer", inquiryAnswer);
		
		req.getRequestDispatcher("/WEB-INF/views/customer_service/inquirydetail.jsp").forward(req, resp);
	}
	
}

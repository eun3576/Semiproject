package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ManagerInquiryAnswer;
import service.face.InquiryAnswerService;
import service.impl.InquiryAnswerServiceImpl;

@WebServlet("/inquiryanswer/delete")
public class InquiryAnswerDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//Service 객체 생성
	private InquiryAnswerService inquiryAnswerService = new InquiryAnswerServiceImpl();
			
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	ManagerInquiryAnswer inquiryAnswer = inquiryAnswerService.getInquiryNo(req);
				
	inquiryAnswerService.delete(inquiryAnswer);
				
	//목록으로 리다이렉트
	resp.sendRedirect("/inquiryanswer/list");	

	}
}

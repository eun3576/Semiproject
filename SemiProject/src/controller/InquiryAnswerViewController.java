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

@WebServlet("/inquiryanswer/view")
public class InquiryAnswerViewController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private InquiryAnswerService inquiryAnswerService = new InquiryAnswerServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/inquiryAnswer/view[GET]");
		
		// 전달 파라미터 얻기
		String inquiryNo = req.getParameter("InquiryNo"); 
		System.out.println("inquiryNo" + inquiryNo);
		
		// 상세보기 결과 조회
		ManagerInquiryAnswer viewInquiryAnswer = inquiryAnswerService.view(inquiryNo);
		
		System.out.println("InquiryAnswerController viewInquiryAnswer - " + viewInquiryAnswer);

		// 조회 결과 MODEL값 전달
		req.setAttribute("viewInquiryAnswer", viewInquiryAnswer);

		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/inquiryAnswer/view.jsp").forward(req, resp);
	}
	
}

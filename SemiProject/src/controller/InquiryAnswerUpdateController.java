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

@WebServlet("/inquiryanswer/update")
public class InquiryAnswerUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private InquiryAnswerService inquiryAnswerService = new InquiryAnswerServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//전달파라미터 얻기 
		ManagerInquiryAnswer inquiryNo = inquiryAnswerService.getInquiryNo(req);

		//상세보기 결과 조회
		ManagerInquiryAnswer updateInquiryAnswer = inquiryAnswerService.view(String.valueOf(inquiryNo.getInquiryNo())); 
				
		//조회결과 MODEL값 전달
		req.setAttribute("updateInquiryAnswer", updateInquiryAnswer);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/inquiryAnswer/update.jsp").forward(req, resp);
	
	}
	
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			inquiryAnswerService.update(req);
			
			resp.sendRedirect("/inquiryAnswer/list");
		
	}
}

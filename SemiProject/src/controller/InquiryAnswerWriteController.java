package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.InquiryAnswerService;
import service.impl.InquiryAnswerServiceImpl;

@WebServlet("/inquiryAnswer/write") 
public class InquiryAnswerWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private InquiryAnswerService inquiryAnswerService = new InquiryAnswerServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/inquiryAnswer/write.jsp").forward(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		//작성글 삽입
		inquiryAnswerService.write(req);
		
		//목록으로 리다이렉션
		resp.sendRedirect("/inquiryanswer/list");
	}
	
}

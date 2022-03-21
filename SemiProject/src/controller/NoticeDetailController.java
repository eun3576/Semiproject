package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Notice;
import service.face.NoticeService;
import service.impl.NoticeServiceImpl;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Notice notice = new Notice();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		NoticeService noticeService = new NoticeServiceImpl();
		
		//문의글의 총 개수 가져오기
		int cntList = noticeService.cntList();
		
		//문의글 총 개수 전달
		req.setAttribute("cntList", cntList);
		
		req.getRequestDispatcher("/WEB-INF/views/customer_service/noticedetail.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NoticeService noticeService = new NoticeServiceImpl();
		
		//문의글 DTO 가져오기
		Notice notice = noticeService.getNoticeDetail(req);
		
		//문의글 전달
		req.setAttribute("notice", notice);

		req.getRequestDispatcher("/WEB-INF/views/customer_service/noticedetailajaxpage.jsp").forward(req, resp);
	}

}

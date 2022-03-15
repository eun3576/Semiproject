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
		int cntList = noticeService.cntList();
		req.setAttribute("cntList", cntList);
		req.getRequestDispatcher("/WEB-INF/views/customer_service/noticedetail.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NoticeService noticeService = new NoticeServiceImpl();
		Notice notice = noticeService.getNoticeDetail(req);
		
		req.setAttribute("notice", notice);

		req.getRequestDispatcher("/WEB-INF/views/customer_service/noticedetailajaxpage.jsp").forward(req, resp);
	}

}

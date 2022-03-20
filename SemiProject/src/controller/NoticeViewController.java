package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ManagerNotice;
import service.face.ManagerNoticeService;
import service.impl.ManagerNoticeServiceImpl;

@WebServlet("/managernotice/view")
public class NoticeViewController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private ManagerNoticeService noticeService = new ManagerNoticeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/notice/view[GET]");
		
		// 전달 파라미터 얻기 - noticeNo
		String noticeNo = req.getParameter("noticeNo");
		System.out.println("noticeNo" + noticeNo);
		
		// 상세보기 결과 조회
		ManagerNotice viewNotice = noticeService.view(noticeNo);
		
		System.out.println("NoticeController viewNotice - " + viewNotice);

		// 조회 결과 MODEL값 전달
		req.setAttribute("viewNotice", viewNotice);

		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/notice/view.jsp").forward(req, resp);
	}
}
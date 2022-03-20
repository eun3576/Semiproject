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

@WebServlet("/notice/delete")
public class NoticeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//NoticeService 객체 생성
	private ManagerNoticeService noticeService = new ManagerNoticeServiceImpl();
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	ManagerNotice notice = noticeService.getNoticeNo(req);
			
	noticeService.delete(notice);
			
	//목록으로 리다이렉트
	resp.sendRedirect("/managernotice/list");	

	}
}

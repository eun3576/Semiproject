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

@WebServlet("/notice/update")
public class NoticeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ManagerNoticeService noticeService = new ManagerNoticeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//전달파라미터 얻기
		ManagerNotice noticeNo = noticeService.getNoticeNo(req);

		//상세보기 결과 조회
		ManagerNotice updateNotice = noticeService.view(String.valueOf(noticeNo.getNoticeNo())); 
				
		//조회결과 MODEL값 전달
		req.setAttribute("updateNotice", updateNotice);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/notice/update.jsp").forward(req, resp);
	
	}
	
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			noticeService.update(req);
			
			resp.sendRedirect("/managernotice/list");
		
	}
}

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
import util.Paging;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	NoticeService noticeService = new NoticeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달파라미터에서 현재 페이징 객체 알아내기
		Paging paging = noticeService.getPaging(req);
				
		//현재 페이지에 해당하는 문의글 리스트 가져오기
		List<Notice> list = noticeService.getNoticeList(paging);
		
		//문의글 리스트 전달
		req.setAttribute("list", list);
		
		//페이징 전달
		req.setAttribute("paging", paging);
		
		req.getRequestDispatcher("/WEB-INF/views/customer_service/noticelist.jsp").forward(req, resp);
		
	}
	
}

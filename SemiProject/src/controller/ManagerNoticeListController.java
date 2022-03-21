package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ManagerNotice;
import service.face.ManagerNoticeService;
import service.impl.ManagerNoticeServiceImpl;
import util.Paging;

@WebServlet("/managernotice/list")
public class ManagerNoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ManagerNoticeService noticeService = new ManagerNoticeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//전달 파라미터에서 현재 페이징 객체 알아내기
		Paging paging = noticeService.getPaging(req);
		System.out.println("NoticeController doGet() - " + paging);

		//공지사항 페이징 목록 조회
		List<ManagerNotice> noticeList = noticeService.getList( paging ); 
		
		String search = req.getParameter("search");
		if(search != null && search.length() > 0) {
			noticeList = noticeService.getList( paging, search ); 
		}
		
		//조회결과 MODEL값 전달
		req.setAttribute("noticeList", noticeList);
		
		//페이징 MODEL값 전달
		req.setAttribute("paging", paging);
		
		//VIEW 지정 및 응답
		req.getRequestDispatcher("/WEB-INF/views/notice/list.jsp").forward(req, resp);
		
	}

}

package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ManagerInquiryAnswer;
import service.face.InquiryAnswerService;
import service.impl.InquiryAnswerServiceImpl;
import util.Paging;

@WebServlet("/inquiryanswer/list")
public class InquiryAnswerListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InquiryAnswerService inquiryAnswerService = new InquiryAnswerServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	//전달파라미터에서 현재 페이징 객체 알아내기
	Paging paging = inquiryAnswerService.getPaging(req);
	System.out.println("[TEST]" + paging);
	
	//문의답변글 페이징 목록 조회
	List<ManagerInquiryAnswer>	inquiryAnswerList = inquiryAnswerService.getList( paging );
	
	//검색창 만들기
	String search = req.getParameter("search");
	if(search != null && search.length() > 0) {
		inquiryAnswerList = inquiryAnswerService.getList( paging, search ); 
		}	
	
	//조회결과 MODEL값 전달
	req.setAttribute("inquiryAnswerList", inquiryAnswerList);
	
	//페이징 MODEL값 전달
	req.setAttribute("paging",paging);
		
	//VIEW 지정 및 응답
	req.getRequestDispatcher("/WEB-INF/views/inquiryAnswer/list.jsp").forward(req, resp);	
	

	
	
	}
}

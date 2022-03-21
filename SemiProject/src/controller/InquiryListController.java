package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Inquiry;
import service.face.InquiryService;
import service.impl.InquiryServiceImpl;
import util.Paging;

@WebServlet("/inquiry/list")
public class InquiryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	InquiryService inquiryService = new InquiryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달파라미터에서 현재 페이징 객체 알아내기
		Paging paging = inquiryService.getPaging(req);
		
		//해당 페이지의 문의글 목록 가져오기
		List<Inquiry> list = inquiryService.getInquiryList(paging);
		
		//문의글 리스트 전달
		req.setAttribute("list", list);
		
		//페이징 전달
		req.setAttribute("paging", paging);
		
		req.getRequestDispatcher("/WEB-INF/views/customer_service/inquirylist.jsp").forward(req, resp);
	}

}

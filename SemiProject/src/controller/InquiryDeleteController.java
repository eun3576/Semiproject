package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.InquiryService;
import service.impl.InquiryServiceImpl;

@WebServlet("/inquiry/delete")
public class InquiryDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	InquiryService inquiryService = new InquiryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		inquiryService.deleteInquiry(req);
		
		resp.sendRedirect("/inquiry/list");
	}
	
}

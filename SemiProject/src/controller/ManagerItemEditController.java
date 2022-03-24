package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Product;
import service.face.ManagerService;
import service.face.ProductService;
import service.impl.ManagerServiceImpl;
import service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = "/manager/itemEdit")
public class ManagerItemEditController extends HttpServlet{

	//서비스 객체
	private ManagerService managerService= new ManagerServiceImpl();
	private ProductService productService= new ProductServiceImpl();
		
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//상세보기 결과 조회
		Product product = productService.getProduct(req); 
				
		//조회결과 MODEL값 전달
		req.setAttribute("product", product);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/manager/itemEdit.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		managerService.productEdit(req);
		
		resp.sendRedirect("/managernotice/list");
	}

	
	
}

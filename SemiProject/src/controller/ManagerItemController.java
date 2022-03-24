package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Product;
import service.face.ManagerService;
import service.impl.ManagerServiceImpl;

@WebServlet(urlPatterns = "/manager/item")
public class ManagerItemController extends HttpServlet{

	//서비스 객체
	private ManagerService managerService= new ManagerServiceImpl();
		
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
						
		List<Product> itemList = managerService.selectProductList();
		
		
		String search = req.getParameter("search"); 
		if(search != null && search.length() > 0) { 
			itemList = managerService.selectSearchProductList(search); 
		}
		 
		
		req.setAttribute("itemList", itemList);
		
		req.getRequestDispatcher("/WEB-INF/views/manager/item.jsp").forward(req, resp); // 로그인 폼 보여주기
	
	}

	
	
}

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

@WebServlet("/manager/itemAdd")
public class ManagerItemAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ManagerService managerService = new ManagerServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/manager/itemAdd.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		managerService.insertProduct(req);
		
		List<Product> itemList = managerService.selectProductList();


		String search = req.getParameter("search"); 
		if(search != null && search.length() > 0) { 
			itemList = managerService.selectSearchProductList(search); 
		}


		req.setAttribute("itemList", itemList);
		
		req.getRequestDispatcher("/WEB-INF/views/manager/item.jsp").forward(req, resp);
	}
}

package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Product;
import dto.ProductCategory;
import service.face.ProductService;
import service.impl.ProductServiceImpl;
import util.ProductPaging;

@WebServlet("/product/result")
public class ProductResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProductService productService = new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달파라미터에서 현재 페이징 객체 알아내기
		ProductPaging paging = productService.getPaging(req);
		
		//페이지에 해당하는 제품 리스트 가져오기
		List<Product> list = productService.getProductList(req, paging);
		
		//카테고리 DTO 리스트 생성
		List<ProductCategory> categoryList = new ArrayList<>();
		
		//제품 번호를 이용하여 카테고리 리스트 가져오기
		for(int i=0; i<list.size(); i++) {
			for(int j=0; j<productService.getCategoryList(list.get(i)).size(); j++) {
				categoryList.add(productService.getCategoryList(list.get(i)).get(j));
			}
		}
		
		//제품 리스트 전달
		req.setAttribute("list", list);
		
		//카테고리 리스트 전달
		req.setAttribute("categoryList", categoryList);
		
		//페이징 전달
		req.setAttribute("paging", paging);
		
		//요청 URL의 값이 존재하면 전달
		if("child".equals(req.getParameter("child"))) {
			req.setAttribute("child", req.getParameter("child"));
		}
		if("woman".equals(req.getParameter("woman"))) {
			req.setAttribute("woman", req.getParameter("woman"));
		}
		if("man".equals(req.getParameter("man"))) {
			req.setAttribute("man", (String)req.getParameter("man"));
		}
		if("aged".equals(req.getParameter("aged"))) {
			req.setAttribute("aged", (String)req.getParameter("aged"));
		}
		if("eye".equals(req.getParameter("eye"))) {
			req.setAttribute("eye", (String)req.getParameter("eye"));
		}
		if("intestine".equals(req.getParameter("intestine"))) {
			req.setAttribute("intestine", (String)req.getParameter("intestine"));
		}
		if("vitamin".equals(req.getParameter("vitamin"))) {
			req.setAttribute("vitamin", (String)req.getParameter("vitamin"));
		}
		if("exercise".equals(req.getParameter("exercise"))) {
			req.setAttribute("exercise", (String)req.getParameter("exercise"));
		}
		
		req.getRequestDispatcher("/WEB-INF/views/product_view/productList.jsp").forward(req, resp);
	}
}

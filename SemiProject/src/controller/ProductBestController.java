package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Product;
import service.face.ProductService;
import service.impl.ProductServiceImpl;

@WebServlet("/product/best")
public class ProductBestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProductService productService = new ProductServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			System.out.println("/product/best [GET]");
			
			//조회 수 높은 상위 3품목 리스트 가져오기
			List<Product> productList = productService.getProductByBest();
			
			//List 저장 되었는지 TEST
			System.out.println(productList);
			
			//조회결과 MODEL값 전달 -  req.Attribute
			req.setAttribute("productList", productList);
			
			//VIEW 지정 및 응답 - forward
			req.getRequestDispatcher("/WEB-INF/views/product_view/productBest.jsp").forward(req, resp);
		}
}

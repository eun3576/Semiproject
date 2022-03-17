package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Product;
import service.face.ProductService;
import service.impl.ProductServiceImpl;

@WebServlet("/product/detail")
public class ProductDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProductService productService = new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		productService.updateviews(req);
		
		Product product = productService.getProduct(req);
		
		req.setAttribute("product", product);
		
		req.getRequestDispatcher("/WEB-INF/views/product_view/productDetail.jsp").forward(req, resp);
	}

}

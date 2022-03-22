package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Attachment;
import dto.Product;
import dto.ProductCategory;
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
		
		List<ProductCategory> list = productService.getCategoryList(req);
		
		List<Attachment> atlist = productService.getAttachmentByNo(req);
		
		req.setAttribute("product", product);
		req.setAttribute("list", list);
		req.setAttribute("atlist", atlist);
		
		req.getRequestDispatcher("/WEB-INF/views/product_view/productDetail.jsp").forward(req, resp);
	}
	
	
}

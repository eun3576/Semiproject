package service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.ProductSearchDao;
import dao.impl.ProductSearchDaoImpl;
import dto.Product;
import dto.ProductCategory;
import service.face.ProductService;

public class ProductServiceImpl implements ProductService{
	Connection conn = JDBCTemplate.getConnection();
	ProductSearchDao productSearchDao = new ProductSearchDaoImpl(); 
	
	@Override
	public List<Product> getProductList(HttpServletRequest req) {
		
		List<ProductCategory> productList = new ArrayList<>();
		
		
		if(req.getParameter("child") != null && "child".equals(req.getParameter("child"))) {
			ProductCategory child = new ProductCategory();
			child.setCategory_name(req.getParameter("child"));
			productList.add(child);
		}
		if(req.getParameter("woman") != null && "woman".equals(req.getParameter("woman"))) {
			ProductCategory woman = new ProductCategory();
			woman.setCategory_name(req.getParameter("woman"));
			productList.add(woman);
		}
		if(req.getParameter("man") != null && "man".equals(req.getParameter("man"))) {
			ProductCategory man = new ProductCategory();
			man.setCategory_name(req.getParameter("man"));
			productList.add(man);
		}
		if(req.getParameter("aged") != null && "aged".equals(req.getParameter("aged"))) {
			ProductCategory aged = new ProductCategory();
			aged.setCategory_name(req.getParameter("aged"));
			productList.add(aged);
		}
		if(req.getParameter("eye") != null && "eye".equals(req.getParameter("eye"))) {
			ProductCategory eye = new ProductCategory();
			eye.setCategory_name(req.getParameter("eye"));
			productList.add(eye);
		}
		if(req.getParameter("intestine") != null && "intestine".equals(req.getParameter("intestine"))) {
			ProductCategory intestine = new ProductCategory();
			intestine.setCategory_name(req.getParameter("intestine"));
			productList.add(intestine);
		}
		if(req.getParameter("vitamin") != null && "vitamin".equals(req.getParameter("vitamin"))) {
			ProductCategory vitamin = new ProductCategory();
			vitamin.setCategory_name(req.getParameter("vitamin"));
			productList.add(vitamin);
		}
		if(req.getParameter("exercise") != null && "exercise".equals(req.getParameter("exercise"))) {
			ProductCategory exercise = new ProductCategory();
			exercise.setCategory_name(req.getParameter("exercise"));
			productList.add(exercise);
		}
		
		return productSearchDao.selectProductList(conn, productList);
	}
	
	@Override
	public Product getProduct(HttpServletRequest req) {
		Product product = new Product();
		product.setProduct_no(Integer.parseInt(req.getParameter("product_no")));
		
		return productSearchDao.selectProduct(conn, product);
	}
	
	@Override
	public void updateviews(HttpServletRequest req) {
		Product product = getProduct(req);
		int views = product.getProduct_views() + 1;
		product.setProduct_views(views);
		int res = productSearchDao.updateviews(conn, product);
		
		if(res > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
	}
}

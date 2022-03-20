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
import util.Paging;
import util.ProductPaging;

public class ProductServiceImpl implements ProductService{
	Connection conn = JDBCTemplate.getConnection();
	ProductSearchDao productSearchDao = new ProductSearchDaoImpl();
	
	@Override
	public List<Product> getProductList(HttpServletRequest req, ProductPaging paging) {
		
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
		
		List<Product> list = productSearchDao.selectProductList(conn, productList, paging);
		
		for(int i=0; i<list.size(); i++) {
			list.get(i).setProduct_content(productSearchDao.selectProduct(conn, list.get(i)).getProduct_content());
		}
		
		return list;
	}
	
	@Override
	public ProductPaging getPaging(HttpServletRequest req) {
		
		String sql = "";
		sql += "select count(*) cnt from ( ";
		sql += "select distinct p.product_no product_no, product_name, product_views, product_img ";
		sql += "from product p, product_category c ";
		sql += "where p.product_no = c.product_no and ( ";
		
		List<String> nullcheck = new ArrayList<>();
		List<String> paramList = new ArrayList<>();
		
		nullcheck.add(req.getParameter("child"));
		nullcheck.add(req.getParameter("woman"));
		nullcheck.add(req.getParameter("man"));
		nullcheck.add(req.getParameter("aged"));
		nullcheck.add(req.getParameter("eye"));
		nullcheck.add(req.getParameter("intestine"));
		nullcheck.add(req.getParameter("vitamin"));
		nullcheck.add(req.getParameter("exercise"));
		
		for(int i=0; i<nullcheck.size(); i++) {
			if(nullcheck.get(i) != null) {
				paramList.add(nullcheck.get(i));
			}
		}
		
		System.out.println(paramList);
		
		for(int i=0; i<paramList.size()-1; i++) {
			sql += "c.category_name='"+paramList.get(i)+"' or ";
		}
		
		int lastcheck = paramList.size();
		
		sql += "c.category_name='"+paramList.get(lastcheck-1)+"' ";
		
		sql += " ) ORDER BY p.product_no DESC )";
		
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param != null && !"".equals( param ) ) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARN] BoardService getPaging() - curPage값이 null이거나 비어있음");
		}
		
		int totalCount = productSearchDao.productCntAll(conn, sql);
		
		ProductPaging paging = new ProductPaging(totalCount, curPage);
		
		return paging;
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
	
	@Override
	public List<ProductCategory> getCategoryList(HttpServletRequest req) {
		
		Product product = new Product();
		product.setProduct_no(Integer.parseInt(req.getParameter("product_no")));
		
		return productSearchDao.selectCategoryList(conn, product);
	}
	
	@Override
	public List<ProductCategory> getCategoryList(Product product) {
		
		List<ProductCategory> list = productSearchDao.selectCategoryList(conn, product);
		
		return list;
	}
}

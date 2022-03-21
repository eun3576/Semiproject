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
		
		//사용자가 선택한 카테고리를 저장할 리스트 생성
		List<ProductCategory> productList = new ArrayList<>();
		
		//체크박스를 선택했다면 해당하는 값을 리스트에 저장
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
		
		//체크한 리스트와 페이징 객체를 전달하여 페이지에 보여질 제품리스트 가져오기
		List<Product> list = productSearchDao.selectProductList(conn, productList, paging);
		
		//제품 DTO에 내용을 추가적으로 저장함
		//-> 오라클 long타입은 where절 조건이 지정안되는 현상 때문에
		for(int i=0; i<list.size(); i++) {
			list.get(i).setProduct_content(productSearchDao.selectProduct(conn, list.get(i)).getProduct_content());
		}
		
		//제품 리스트 반환
		return list;
	}
	
	@Override
	public ProductPaging getPaging(HttpServletRequest req) {
		
		String sql = "";
		sql += "select count(*) cnt from ( ";
		sql += "select distinct p.product_no product_no, product_name, product_views, product_img ";
		sql += "from product p, product_category c ";
		sql += "where p.product_no = c.product_no and ( ";
		
		//전체 전달 값들을 저장할 공간
		List<String> nullcheck = new ArrayList<>();
		
		//nullcheck리스트에 있는 null값이 아닌 결과들을 저장할 공간
		List<String> paramList = new ArrayList<>();
		
		//체크박스의 모든 값들을 가져온다
		//-> 체크안된것은 null로 반환되어 저장됨
		nullcheck.add(req.getParameter("child"));
		nullcheck.add(req.getParameter("woman"));
		nullcheck.add(req.getParameter("man"));
		nullcheck.add(req.getParameter("aged"));
		nullcheck.add(req.getParameter("eye"));
		nullcheck.add(req.getParameter("intestine"));
		nullcheck.add(req.getParameter("vitamin"));
		nullcheck.add(req.getParameter("exercise"));
		
		//null값을 제외한 데이터들을 저장
		for(int i=0; i<nullcheck.size(); i++) {
			if(nullcheck.get(i) != null) {
				paramList.add(nullcheck.get(i));
			}
		}
		
		//sql문의 반복처리를 위한 반복
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
		
		//제품 DTO 생성
		Product product = new Product();
		
		//제품번호를 가져온다
		product.setProduct_no(Integer.parseInt(req.getParameter("product_no")));
		
		//제품번호에 해당하는 제품 데이터를 가져와 반환
		return productSearchDao.selectProduct(conn, product);
	}
	
	@Override
	public void updateviews(HttpServletRequest req) {
		
		//해당 제품의 DTO를 가져온다
		Product product = getProduct(req);
		
		//조회수 +1 처리
		int views = product.getProduct_views() + 1;
		
		//처리된 조회수를 다시 저장
		product.setProduct_views(views);
		
		//수정된 값을 DB에 저장
		int res = productSearchDao.updateviews(conn, product);
		
		//결과
		if(res > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
	}
	
	@Override
	public List<ProductCategory> getCategoryList(HttpServletRequest req) {
		
		//제품 DTO생성
		Product product = new Product();
		
		//제품 번호를 가져온다
		product.setProduct_no(Integer.parseInt(req.getParameter("product_no")));
		
		//제품 번호에 해당하는 카테고리들을 가져온 후 반환
		return productSearchDao.selectCategoryList(conn, product);
	}
	
	@Override
	public List<ProductCategory> getCategoryList(Product product) {
		
		//카테고리 리스트 가져오기
		List<ProductCategory> list = productSearchDao.selectCategoryList(conn, product);
		
		return list;
	}
}

package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Product;
import dto.ProductCategory;
import util.ProductPaging;

public interface ProductSearchDao {
	public List<Product> selectProductList(Connection conn, List<ProductCategory> categoryList, ProductPaging paging);
	
	public Product selectProduct(Connection conn, Product product);
	
	public int updateviews(Connection conn, Product product);
	
	public List<ProductCategory> selectCategoryList(Connection conn, Product product);
	
	public int productCntAll(Connection conn,String sql);
	
	
}

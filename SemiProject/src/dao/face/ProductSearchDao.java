package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Product;
import dto.ProductCategory;

public interface ProductSearchDao {
	public List<Product> selectProductList(Connection conn, List<ProductCategory> categoryList);
	
	public Product selectProduct(Connection conn, Product product);
	
	public int updateviews(Connection conn, Product product);
}

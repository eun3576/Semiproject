package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Product;
import dto.ProductCategory;
import util.ProductPaging;

public interface ProductService {
	
	public List<Product> getProductList(HttpServletRequest req, ProductPaging paging);
	
	public Product getProduct(HttpServletRequest req);
	
	public void updateviews(HttpServletRequest req);
	
	public List<ProductCategory> getCategoryList(HttpServletRequest req);
	
	public List<ProductCategory> getCategoryList(Product product);
	
	public ProductPaging getPaging(HttpServletRequest req);
}

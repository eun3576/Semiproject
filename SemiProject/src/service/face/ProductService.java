package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Product;
import dto.ProductCategory;

public interface ProductService {
	
	public List<Product> getProductList(HttpServletRequest req);
	
	public Product getProduct(HttpServletRequest req);
	
	public void updateviews(HttpServletRequest req);
	
	public List<ProductCategory> getCategoryList(HttpServletRequest req);
	
	public List<ProductCategory> getCategoryList(Product product);
}

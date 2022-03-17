package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Product;

public interface ProductService {
	
	public List<Product> getProductList(HttpServletRequest req);
	
	public Product getProduct(HttpServletRequest req);
	
	public void updateviews(HttpServletRequest req);
}

package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Attachment;
import dto.Product;
import dto.ProductCategory;
import util.ProductPaging;

public interface ProductService {
	
	/**
	 * 페이지에 해당하는 제품 목록을 가져온다
	 * @param req 요청 정보 객체
	 * @param paging 페이지 정보
	 * @return 해당 페이지에 들어갈 제품 리스트
	 */
	public List<Product> getProductList(HttpServletRequest req, ProductPaging paging);
	
	/**
	 * 제품 DTO를 가져온다
	 * @param req 요청 정보 객체
	 * @return 제품 DTO 반환
	 */
	public Product getProduct(HttpServletRequest req);
	
	/**
	 * 조회수를 올려주는 기능
	 * @param req 요청 정보 객체
	 */
	public void updateviews(HttpServletRequest req);
	
	/**
	 * 카테고리 목록을 가져오는 기능
	 * @param req 요청 정보 객체
	 * @return 카테고리 목록
	 */
	public List<ProductCategory> getCategoryList(HttpServletRequest req);
	
	/**
	 * 카테고리 목록을 가져오는 기능
	 * @param product 제품 DTO
	 * @return 카테고리 목록
	 */
	public List<ProductCategory> getCategoryList(Product product);
	
	/**
	 * 페이지 정보를 가져온다
	 * @param req 요청 정보 객체
	 * @return 페이지 처리된 결과
	 */
	public ProductPaging getPaging(HttpServletRequest req);
	
	/**
	 * 해당 상품의 첨부파일을 가져온다
	 * @param req 요청 정보 객체
	 * @return 첨부파일리스트
	 */
	public List<Attachment> getAttachmentByNo(HttpServletRequest req);
}

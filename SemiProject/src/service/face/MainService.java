package service.face;

import java.util.List;

import dto.Product;
import dto.ProductTag;
import dto.Review;

public interface MainService {

	/**
	 * 베스트 상품 목록 조회
	 * @return 조회된 상품 목록
	 */
	public List<ProductTag> getBestList();

	/**
	 * 조회수가 높은 리뷰 조회
	 * @return 조회된 리뷰 목록
	 */
	public List<Review> getReviewList();

	/**
	 * 검색어 파라미터를 단어별로 나눠 배열로 받는다
	 * @param search 배열로 만들 String 
	 * @return String 배열
	 */
	public String[] divideSearch(String search);

	/**
	 * 배열로 받은 검색어로 상품을 검색한다
	 * @param searchItems 검색할 배열
	 * @return 해당하는 상품 리스트
	 */
	public List<Product> searchBysearchItems(String[] searchItems);

}

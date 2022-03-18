package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Product;
import dto.ProductTag;
import dto.Review;

public interface MainDao {

	/**
	 * BEST상품 조회
	 * @param conn db연결 객체 
	 * @return 조회된 상품 목록
	 */
	public List<ProductTag> selectProductNtag(Connection conn);

	/**
	 * 조회수 높은 리뷰 조회
	 * @param conn db연결 객체 
	 * @return 조회된 리뷰 목록
	 */
	public List<Review> selectReviewByViews(Connection conn);

	/**
	 * 검색어에 해당하는 상품 list가져오기
	 * @param conn db연결 객체
	 * @param searchItems 검색어(들)
	 * @return 검색어에 해당하는 객체
	 */
	public List<Product> selectBySearchItems(Connection conn, String[] searchItems);

}

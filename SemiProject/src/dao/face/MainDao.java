package dao.face;

import java.sql.Connection;
import java.util.List;

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

}

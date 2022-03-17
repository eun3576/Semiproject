package service.face;

import java.util.List;

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

}

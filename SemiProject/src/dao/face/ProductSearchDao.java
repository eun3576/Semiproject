package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Attachment;
import dto.Notice;
import dto.Product;
import dto.ProductCategory;
import util.ProductPaging;

public interface ProductSearchDao {
	
	/**
	 * 페이지에 해당하는 제품 리스트 가져오기
	 * @param conn DB연결 객체
	 * @param categoryList 사용자가 선택한 체크박스 값들
	 * @param paging 페이지 개수 정보
	 * @return 페이지에 해당하는 제품 리스트
	 */
	public List<Product> selectProductList(Connection conn, List<ProductCategory> categoryList, ProductPaging paging);
	
	/**
	 * 제품 데이터 가져오기
	 * @param conn DB연결 객체
	 * @param product 제품 번호가 입력되어있는 DTO 객체
	 * @return 제품 DTO 객체
	 */
	public Product selectProduct(Connection conn, Product product);
	
	/**
	 * 조회수 증가
	 * @param conn DB연결 객체
	 * @param product 제품 DTO 객체
	 * @return 결과값 1-성공, 0-실패
	 */
	public int updateviews(Connection conn, Product product);
	
	/**
	 * 제품번호에 해당하는 카테고리 리스트 가져오기
	 * @param conn DB연결 객체
	 * @param product 제품번호
	 * @return 카테고리리스트 반환
	 */
	public List<ProductCategory> selectCategoryList(Connection conn, Product product);
	
	/**
	 * 전체 제품 개수를 확인
	 * @param conn DB연결 객체
	 * @param sql 사용자가 선택한 체크박스에 따라 바뀜
	 * @return 사용자가 선택한 체크박스에 해당하는 전체 제품 수
	 */
	public int productCntAll(Connection conn,String sql);
	
	/**
	 * 제품의 첨부파일을 가져온다
	 * @param conn DB연결 객체
	 * @param notice 제품을 식별할 수 있는 데이터
	 * @return 해당되는 제품의 첨부파일리스트 반환
	 */
	public List<Attachment> getAttachmentList(Connection conn, Product product);
	
	/**
	 * 제품의 조회수 상위 3품목을 조회한다.
	 * 
	 * @param conn DB연결 객체
	 * @param product 제품 정보 객체
	 * @return 조회수 상위 3품목 리스트
	 */
	public List<Product> getProductByViews(Connection conn, Product product);
	
}

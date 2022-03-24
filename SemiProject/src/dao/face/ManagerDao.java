package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Attachment;
import dto.Manager;
import dto.Product;
import dto.ProductCategory;
import dto.Review;
import dto.UserInfo;

public interface ManagerDao {

	/**
	 * id와 password가 일치하는 관리자를 조회한다
	 * -> 로그인 인증에 사용
	 * 
	 * @param conn - DB연결 객체
	 * @param manager - 조회할 관리자 정보
	 * @return - 0 : 존재하지 않는 , 1 : 존재하는
	 */
	public int selectCntManagerByIdPassword(Connection conn, Manager manager);

	/**
	 * id를 이용해 관리자 정보 조회하기
	 * 
	 * @param conn - DB연결 객체
	 * @param manager - 조회할 id를 가진 객체
	 * @return Manager - 조회된 관리자 정보
	 */
	public Manager selectManagerById(Connection conn, Manager manager);

	/* 회원 정보 조회 */
	public List<UserInfo> selectUserList(Connection conn);
	
	/* 회원 정보 삭제 */
	public void userDelete(Connection conn, UserInfo userInfo);
	
	/* 회원 닉네임 검색 */
	public List<UserInfo> selectUserSearchList(Connection conn, String search);
	
	
	/* 리뷰 게시글 삭제 */
	public void reviewDelete(Connection conn, Review review);

	//리뷰 이미지 삭제
	public int reviewImgDelete(Connection conn, Review review);
	
	public int insertProduct(Connection conn, Product product);
	
	public Product selectProductNo(Connection conn);
	
	public int insertAttachment(Connection conn, Attachment attachment);
	
	public int insertProductCategory(Connection conn, ProductCategory productCategory);
	
	/* 제품 리스트 */
	public List<Product> selectProductList(Connection conn);
	
	/* 제품 검색 리스트 */
	public List<Product> selectSearchProductList(Connection conn, String search);
	
	//제품 수정
	public int productEdit(Connection conn, Product product);

	//제품 삭제
	public void productDelete(Connection conn, Product product);
	
	//제품 카테고리 삭제
	public int productCategoryDelete(Connection conn, Product product);
	
	//제품 이미지 삭제
	public int productImgDelete(Connection conn, Product product);
	
}

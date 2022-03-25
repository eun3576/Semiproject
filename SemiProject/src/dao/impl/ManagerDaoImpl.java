package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ManagerDao;
import dto.Attachment;
import dto.Manager;
import dto.Product;
import dto.ProductCategory;
import dto.Review;
import dto.UserInfo;

public class ManagerDaoImpl implements ManagerDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public int selectCntManagerByIdPassword(Connection conn, Manager manager) {

		String sql = "";
		sql += " SELECT COUNT(*) FROM MANAGER ";
		sql += " WHERE ID =  ? ";
		sql += " AND password = ? ";
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, manager.getId());
			ps.setString(2, manager.getPassword());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return cnt;
	}
	
	@Override
	public Manager selectManagerById(Connection conn, Manager manager) {
		
		String sql = "";
		sql += " SELECT ID, PASSWORD, MANAGER_NO ";
		sql += " FROM MANAGER ";
		sql += " WHERE ID = ? ";
		sql += " AND PASSWORD = ? ";
		
		//조회 결과를 저장할 DTO
		Manager result = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, manager.getId());
			ps.setString(2, manager.getPassword());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				result = new Manager();
				
				result.setId(rs.getString("id") );
				result.setPassword(rs.getString("password") );
				result.setManager_no(rs.getInt("MANAGER_NO") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return result;
		
	}
	
	@Override
	public List<UserInfo> selectUserList(Connection conn) {
		
		String sql = "select * from userinfo order by user_no asc";
				
		//결과 저장할 List
		List<UserInfo> userList = new ArrayList<>();  
		
		try {
			ps = conn.prepareStatement(sql); 
			rs = ps.executeQuery(); 
			
			while( rs.next() ) {
				UserInfo n = new UserInfo(); 
				
				n.setUserNo(rs.getInt("user_no"));
				n.setId(rs.getString("id"));
				n.setPassword(rs.getString("password"));
				n.setGender(rs.getString("gender"));
				n.setNickname(rs.getNString("nickname"));
				n.setSymptom(rs.getNString("sympton"));
				n.setPhonenumber(rs.getNString("phonenumber"));
				
				userList.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return userList;
			
	}

	@Override
	public void userDelete(Connection conn, UserInfo userInfo) {
			
		String sql = "delete userinfo where user_no=?";		
	
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userInfo.getUserNo());
			
			res = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		if(res>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
	}

	@Override
	public List<UserInfo> selectUserSearchList(Connection conn, String search) {
		
		String sql = "select * from userinfo where id like ?";
		
		//결과 저장할 List
		List<UserInfo> userList = new ArrayList<>();  
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,"%" + search + "%");
			rs = ps.executeQuery(); 
			
			while( rs.next() ) {
				UserInfo n = new UserInfo(); 
				
				n.setUserNo(rs.getInt("user_no"));
				n.setId(rs.getString("id"));
				n.setPassword(rs.getString("password"));
				n.setGender(rs.getString("gender"));
				n.setNickname(rs.getNString("nickname"));
				n.setSymptom(rs.getNString("sympton"));
				n.setPhonenumber(rs.getNString("phonenumber"));
				
				userList.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return userList;
	}

	@Override
	public void reviewDelete(Connection conn, Review review) {
		
		String sql = "delete review where review_no=?";		
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, review.getReview_no());
			
			res = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		if(res>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	
	@Override
	public Product selectProductNo(Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Product product = new Product();
		
		String sql = "";
		sql += "SELECT product_seq.nextval product_no FROM dual";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				product.setProduct_no(rs.getInt("product_no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return product;
	}
	
	@Override
	public int insertProduct(Connection conn, Product product) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int res = 0;
		
		String sql = "";
		sql += "INSERT INTO product(product_no, product_name, product_content, product_views, product_img) ";
		sql += "VALUES(?, ?, ?, 0, ?) ";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, product.getProduct_no());
			ps.setString(2, product.getProduct_name());
			ps.setString(3, product.getProduct_content());
			ps.setString(4, product.getProduct_img());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int insertProductCategory(Connection conn, ProductCategory productCategory) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int res = 0;
		
		String sql = "";
		sql += "INSERT INTO product_category ";
		sql += "VALUES(product_category_seq.nextval, ?, ?) ";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, productCategory.getCategory_name());
			ps.setInt(2, productCategory.getProduct_no());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int insertAttachment(Connection conn, Attachment attachment) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int res = 0;
		
		String sql = "";
		sql += "INSERT INTO attachment(attachment_no, stored_img, origin_img, product_no, filesize) ";
		sql += "VALUES(attachment_seq.nextval, ?, ?, ?, ?) ";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, attachment.getStored_img());
			ps.setString(2, attachment.getOrigin_img());
			ps.setInt(3, attachment.getProduct_no());
			ps.setInt(4, attachment.getFilesize());
			
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public List<Product> selectProductList(Connection conn) {
		
		String sql = "select * from product order by product_no DESC";
		
		//결과 저장할 List
		List<Product> productList = new ArrayList<>();  
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(); 
			
			while( rs.next() ) {
				Product n = new Product(); 
				
				n.setProduct_no(rs.getInt("product_no"));
				n.setProduct_name(rs.getString("product_name"));
				n.setProduct_content(rs.getString("product_content"));
				n.setProduct_views(rs.getInt("product_views"));
				n.setProduct_img(rs.getString("product_img"));
				
				productList.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return productList;
	}

	@Override
	public List<Product> selectSearchProductList(Connection conn, String search) {
		
		String sql = "select * from product where product_name like ? order by product_no asc";
				
		//결과 저장할 List
		List<Product> productList = new ArrayList<>();  
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + search + "%");
			rs = ps.executeQuery(); 
			
			while( rs.next() ) {
				Product n = new Product(); 
				
				n.setProduct_no(rs.getInt("product_no"));
				n.setProduct_name(rs.getString("product_name"));
				n.setProduct_content(rs.getString("product_content"));
				n.setProduct_views(rs.getInt("product_views"));
				n.setProduct_img(rs.getString("product_img"));
				
				productList.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return productList;
	}

	@Override
	public int productEdit(Connection conn, Product product) {
		
		String sql = "update product set product_name = ? , product_content = ?  where product_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, product.getProduct_name());
			ps.setString(2, product.getProduct_content());
			ps.setInt(3, product.getProduct_no());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public void productDelete(Connection conn, Product product) {
		
		String sql = "delete product where product_no = ?";		
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, product.getProduct_no());
			
			res = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		if(res>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
	}

	// 프로젝트 삭제 -> 프로젝트에 있는 카테고리,이미지 테이블에서 이 프로젝트 관련 삭제 -> 프로젝트 DB 삭제
	//  (카테고리,이미지) 테이블 삭제 -> 프로젝트 삭제 -> 
	
	@Override
	public int productCategoryDelete(Connection conn, Product product) {

		String sql = "delete product_category where product_no = ?";		
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, product.getProduct_no());
			
			res = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		
		
		return res;
	}

	@Override
	public int productImgDelete(Connection conn, Product product) {

		String sql = "delete attachment where product_no = ?";		
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, product.getProduct_no());
			
			res = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		
		
		return res;
	}

	@Override
	public int reviewImgDelete(Connection conn, Review review) {
		
		String sql = "delete attachment where review_no = ?";		
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, review.getReview_no());
			
			res = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		
		
		return res;
	}

	@Override
	public int productUpdateImg(Connection conn, Product product) {
		
		String sql = "";
		sql += "UPDATE product set product_img = ? where product_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, product.getProduct_img());
			ps.setInt(2, product.getProduct_no());
			
			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}
	
}

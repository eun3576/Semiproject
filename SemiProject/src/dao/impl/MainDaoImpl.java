package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.MainDao;
import dto.Product;
import dto.ProductTag;
import dto.Review;

public class MainDaoImpl implements MainDao{
	
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //조회결과 객체
	
	@Override
	public List<ProductTag> selectProductNtag(Connection conn) {
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "   SELECT rownum rnum, DATA.* FROM (";
		sql += "		SELECT tag_name, product_views, product_img";
		sql += "        	FROM product, product_tag"; 
		sql += "		WHERE product.product_no = product_tag.product_no"; 
		sql += "		ORDER BY product_views DESC";
		sql += "	) DATA";	    
		sql += " ) R";
		sql += " WHERE rnum BETWEEN 1 AND 4";
		
		//조회결과 저장할 list 
		List<ProductTag> tagList = new ArrayList<>();
		
		try {
			//sql 수행객체 생성
			ps = conn.prepareStatement(sql);
			//sql 수행 및 결과 저장
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductTag productTag = new ProductTag();
				productTag.setTagName(rs.getString("tag_name"));
				productTag.setProductViews(rs.getInt("product_views"));
				productTag.setProductImg(rs.getString("product_img"));
				
				tagList.add(productTag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		return tagList;
	}

	@Override
	public List<Review> selectReviewByViews(Connection conn) {
		String sql = "";
		sql += "SELECT * FROM(";
		sql += "   SELECT rownum rnum, DATA.* FROM(";
		sql += "		SELECT content, views, nickname ";
		sql += "        	FROM review, userinfo "; 
		sql += "		WHERE review.user_no = userinfo.user_no"; 
		sql += "		ORDER BY views DESC";
		sql += "	) DATA";	    
		sql += " ) R";
		sql += " WHERE rnum BETWEEN 1 AND 8";
		
		//조회결과 저장할 list 
		List<Review> rList = new ArrayList<>();
		
		try {
			//sql 수행객체 생성
			ps = conn.prepareStatement(sql);
			//sql 수행 및 결과 저장
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Review review = new Review();
				review.setContent(rs.getString("content"));
				review.setViews(rs.getInt("views"));
				review.setNickname(rs.getString("nickname"));
				
				rList.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		return rList;
	}

	@Override
	public List<Product> selectBySearchItems(Connection conn) {
		String sql = "";
		sql += "SELECT product_name, product_views, product_img, product_content  FROM product";
		sql += " ORDER BY product_views";
		
		List<Product> pList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Product product = new Product();
				
				product.setProduct_content(rs.getString("product_content"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_views(rs.getInt("product_views"));
				product.setProduct_img(rs.getString("product_img"));
				
				pList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//자원해제
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return pList;
	}

}

package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ProductSearchDao;
import dto.Product;
import dto.ProductCategory;

public class ProductSearchDaoImpl implements ProductSearchDao{
	@Override
	public List<Product> selectProductList(Connection conn, List<ProductCategory> categoryList) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT p.product_no, p.category_name, product_name, product_content, product_views, product_img, category_no ";
		sql += "FROM product p, product_category c ";
		sql += "WHERE p.product_no = c.product_no AND (";
		
		for(int i=0; i<categoryList.size(); i++) {
			sql += "p.category_name=?";
			
			if(i != categoryList.size()-1) {
				sql += " or ";
			}
		}

		sql += ")";
		sql += "ORDER BY p.product_no DESC";
		
		List<Product> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			for(int i=1; i<categoryList.size()+1; i++) {
				ps.setString(i, categoryList.get(i-1).getCategory_name());
			}
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Product p = new Product();
				p.setCategory_name(rs.getString("category_name"));
				p.setProduct_content(rs.getString("product_content"));
				p.setProduct_img(rs.getString("product_img"));
				p.setProduct_no(rs.getInt("product_no"));
				p.setProduct_name(rs.getString("product_name"));
				p.setProduct_views(rs.getInt("product_views"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}
	
	@Override
	public Product selectProduct(Connection conn, Product product) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT * FROM product ";
		sql += "WHERE product_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, product.getProduct_no());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				product.setCategory_name(rs.getString("category_name"));
				product.setProduct_content(rs.getString("product_content"));
				product.setProduct_img(rs.getString("product_img"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_views(rs.getInt("product_views"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
				
		return product;
	}
	
	@Override
	public int updateviews(Connection conn, Product product) {
		PreparedStatement ps = null;
		int res = 0;
		
		String sql = "";
		sql += "UPDATE product ";
		sql += "SET product_views = ? ";
		sql += "WHERE product_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, product.getProduct_views());
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

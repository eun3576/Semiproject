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
import util.ProductPaging;

public class ProductSearchDaoImpl implements ProductSearchDao{
	@Override
	public List<Product> selectProductList(Connection conn, List<ProductCategory> categoryList, ProductPaging paging) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "select * from ( ";
		sql += "select rownum rnum, t.* from( ";
		sql += "select distinct p.product_no product_no, product_name, product_views, product_img  ";
		sql += "from product p, product_category c ";
		sql += "where p.product_no = c.product_no and ( ";
		
		for(int i=0; i<categoryList.size(); i++) {
			sql += "c.category_name=?";
			
			if(i != categoryList.size()-1) {
				sql += " or ";
			}
		}

		sql += ") ";
		sql += "ORDER BY p.product_no DESC ";
		sql += ") t ";
		sql += ") where rnum between ? and ?";
		List<Product> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			for(int i=1; i<categoryList.size()+1; i++) {
				ps.setString(i, categoryList.get(i-1).getCategory_name());
			}
			
			ps.setInt(categoryList.size()+1, paging.getStartNo());
			ps.setInt(categoryList.size()+2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Product p = new Product();
				p.setProduct_no(rs.getInt("product_no"));
				p.setProduct_name(rs.getString("product_name"));
				p.setProduct_views(rs.getInt("product_views"));
				p.setProduct_img(rs.getString("product_img"));
				
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
	public int productCntAll(Connection conn, String sql) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int count = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				count = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return count;
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
	
	@Override
	public List<ProductCategory> selectCategoryList(Connection conn, Product product) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductCategory> list = new ArrayList<>();
		
		String sql = "";
		sql += "SELECT * FROM product_category ";
		sql += "WHERE product_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, product.getProduct_no());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductCategory pc = new ProductCategory();
				pc.setCategory_no(rs.getInt("category_no"));
				pc.setCategory_name(rs.getString("category_name"));
				pc.setProduct_no(rs.getInt("product_no"));
				
				list.add(pc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}
	
}

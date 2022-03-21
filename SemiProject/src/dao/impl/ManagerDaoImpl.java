package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.ManagerDao;
import dto.Manager;

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
		
}

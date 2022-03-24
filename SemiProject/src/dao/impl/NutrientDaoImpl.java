package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.NutrientDao;
import dto.Nutrient;

public class NutrientDaoImpl implements NutrientDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
		
	@Override
	public Nutrient selectAllByNutrientNo(Connection conn, Nutrient nutrientno) {
		
		String sql = "";
		sql += "SELECT nutrientno, nutrient, explain";
		sql += " FROM nutrient";
		sql += " WHERE nutrientno = ?";
		
		//조회한 결과를 저장할 객체
		Nutrient n = null;
		
		try {
			//SQL수행 객체 
			ps = conn.prepareStatement(sql);
			
			//SQL수행문 1번째 매개변수에 nutrientNo 대입
			ps.setInt(1, nutrientno.getNutrientNo());
			
			//SQL 수행 및 결과 집합 저장
			rs = ps.executeQuery();
			
			
			if(rs.next()) {
				//결과값 저장 객체
				n = new Nutrient();
				
				//결과값 행처리 
				n.setNutrientNo(rs.getInt("nutrientno"));
				n.setNutrient(rs.getString("nutrient"));
				n.setExplain(rs.getString("explain"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return n;
	}

}

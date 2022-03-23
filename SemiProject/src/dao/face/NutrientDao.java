package dao.face;

import java.sql.Connection;

import dto.Nutrient;

public interface NutrientDao {
	
	/**
	 * NutrientNo를 이용하여 모든 컬럼 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param nutrient - nutrientNo가 있는 DTO객체
	 * @return - 모든 정보가 저장된 Nutrient DTO 객체
	 */
	public Nutrient selectAllByNutrientNo(Connection conn, Nutrient nutrient);
}

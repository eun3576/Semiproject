package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Nutrient;

public interface NutrientService {
	
	/**
	 * 질문에 대한 form 데이터를 받아 3일 이라고 
	 * 답한 질문에 대한 영양소들의 list를 반환!
	 * 
	 * @return - List<Nutrient>
	 */
	public List<Nutrient> getRecommendNutrientList(HttpServletRequest req);

}

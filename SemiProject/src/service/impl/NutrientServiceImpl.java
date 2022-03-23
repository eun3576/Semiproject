package service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.NutrientDao;
import dao.impl.NutrientDaoImpl;
import dto.Nutrient;
import service.face.NutrientService;

public class NutrientServiceImpl implements NutrientService{

	NutrientDao nutrientDao = new NutrientDaoImpl();
	
	@Override
	public List<Nutrient> getRecommendNutrientList(HttpServletRequest req) {
		
		//질문에 대하여 선택 값 불러오기
		int[] answerArr = {Integer.parseInt(req.getParameter("q_1"))
				, Integer.parseInt(req.getParameter("q_2"))
				, Integer.parseInt(req.getParameter("q_3"))
				, Integer.parseInt(req.getParameter("q_4"))
				, Integer.parseInt(req.getParameter("q_5"))
				, Integer.parseInt(req.getParameter("q_6"))
				, Integer.parseInt(req.getParameter("q_7"))
				, Integer.parseInt(req.getParameter("q_8"))};
		
		//nutrientNo를 통하여 조회한 정보를 저장할 List 객체
		List<Nutrient> nutrientList = new ArrayList<>();
		
		for (int i=0; i<answerArr.length; i++) {
			
			//질문에 대한 답 값을 통하여 nutrientNo를 담을 객체
			Nutrient nutrientno = new Nutrient();
			
			if (answerArr[i] <= 2) {
				nutrientno.setNutrientNo(i+1);
				nutrientList.add(nutrientDao.selectAllByNutrientNo(JDBCTemplate.getConnection(), nutrientno));
			} //if end
		} //for end
		
		System.out.println("영양소 추천 정보");
		for( Nutrient n : nutrientList ) System.out.println(n);
		
		return nutrientList;
	}
}

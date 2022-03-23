package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Nutrient;
import service.face.NutrientService;
import service.impl.NutrientServiceImpl;

@WebServlet("/nutrient/recommend")
public class NutrientRecommendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NutrientService nutrientService = new NutrientServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("nutrient/recommend [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/recommend/nutrient_recommend.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("nutrient/recommend [POST]");
		
		//영양소 진단을 통하여 질문에 대한 답 form데이터 답의 value가 2이하 인것들만 전체 조회
		List<Nutrient> nutrientList = nutrientService.getRecommendNutrientList(req);
		//영양소 정보가 다 불러와졌는지 확인
		System.out.println("영양소 정보! + " + nutrientList);
		
		//조회결과 MODEL값 전달 - req.Attribute
		req.setAttribute("nutrientList", nutrientList);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/recommend/nutrient_recommend_result.jsp").forward(req, resp);
	}

}

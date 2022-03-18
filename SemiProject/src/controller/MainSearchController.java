package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Product;
import service.face.MainService;
import service.impl.MainServiceImpl;

@WebServlet("/main/search")
public class MainSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MainService mainService = new MainServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/main/search[GET]");
		
		//인코딩 설정
		req.setCharacterEncoding("UTF-8");
		
		//검색어 받기
		String search = req.getParameter("mSearch");
		
		//변수 선언
		String[] searchItems =null;
		List<Product> product = null;
		
		
		//검색이 빈칸이 아닐 때만 실행
		if(!search.equals("")) {
			//검색어 단어로 나눠 배열로 받기
			searchItems = mainService.divideSearch(search);
			//검색어에 해당하는 상품 리스트로 받기
			product = mainService.searchBysearchItems(searchItems);
		}
		
		//상품리스트 어트리뷰트로 설정
		req.setAttribute("pList", product);
		
		//해당상품리스트를 보여주는 jsp로 포워드
		req.getRequestDispatcher("/WEB-INF/views/main/searchAnwser.jsp").forward(req, resp);
	}
}

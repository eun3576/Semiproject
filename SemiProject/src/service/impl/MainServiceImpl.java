package service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.MainDao;
import dao.impl.MainDaoImpl;
import dto.Product;
import dto.Review;
import service.face.MainService;

public class MainServiceImpl implements MainService{
	
	//DAO 객체
	private MainDao mainDao = new MainDaoImpl();
	
	
	@Override
	public List<Product> getBestList() {
		Connection conn = JDBCTemplate.getConnection();
		
		return mainDao.selectProduct(conn);
	}


	@Override
	public List<Review> getReviewList() {
		Connection conn = JDBCTemplate.getConnection();
		return mainDao.selectReviewByViews(conn);
	}


	@Override
	public String[] divideSearch(String search) {
		
		//문자열을 소문자로 변경한다
		search = search.toLowerCase();
		//문자열을 공백으로 나눈다
		String[] strs = search.split(" ");
		
		//비교 문자열 담을 변수 
		String str = "";

		return strs;
	}


	@Override
	public List<Product> searchBysearchItems(String[] searchItems) {
		Connection conn = JDBCTemplate.getConnection();
		//전체 상품 리스트
		List<Product> pList = mainDao.selectBySearchItems(conn);
		
		//검색어에 해당하는 상품리스트
		List<Product> pResultList = new ArrayList<>();
		Product product = null;
		for(int i=0;i<pList.size();i++) {
			for(int j=0;j<searchItems.length;j++) {
				product = new Product();
				if(pList.get(i).getProduct_content().contains(searchItems[j])) {
						product.setProduct_views(pList.get(i).getProduct_views());
						product.setProduct_img(pList.get(i).getProduct_img());
						product.setProduct_name(pList.get(i).getProduct_name());
						product.setProduct_no(pList.get(i).getProduct_no());
						pResultList.add(product);
				}
			}
		}
		
		//해당 상품 중 중복 제거
		for(int i=0;i<pResultList.size();i++) {
			if(i==pResultList.size()) {break;}
			for(int j=i+1;j<pResultList.size();j++) {
				if(pResultList.get(i).getProduct_name().equals(pResultList.get(j).getProduct_name())) {
					pResultList.remove(j);
				}
			}
		}
		return pResultList;
	}

}

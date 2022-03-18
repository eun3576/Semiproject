package service.impl;

import java.sql.Connection;
import java.util.List;

import common.JDBCTemplate;
import dao.face.MainDao;
import dao.impl.MainDaoImpl;
import dto.Product;
import dto.ProductTag;
import dto.Review;
import service.face.MainService;

public class MainServiceImpl implements MainService{
	
	//DAO 객체
	private MainDao mainDao = new MainDaoImpl();
	
	
	@Override
	public List<ProductTag> getBestList() {
		Connection conn = JDBCTemplate.getConnection();
		
		return mainDao.selectProductNtag(conn);
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
		return strs;
	}


	@Override
	public List<Product> searchBysearchItems(String[] searchItems) {
		Connection conn = JDBCTemplate.getConnection();
		List<Product> pList = mainDao.selectBySearchItems(conn, searchItems);
		return pList;
	}

}

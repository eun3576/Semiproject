package service.impl;

import java.sql.Connection;
import java.util.List;

import common.JDBCTemplate;
import dao.face.MainDao;
import dao.impl.MainDaoImpl;
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

}

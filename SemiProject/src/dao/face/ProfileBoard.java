package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Review;
import dto.UserInfo;

public interface ProfileBoard {
	
	public List<Review> Blist(Connection conn , UserInfo userinfo);
	
	

}

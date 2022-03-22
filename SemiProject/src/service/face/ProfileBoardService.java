package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Review;

public interface ProfileBoardService {
	
	public List<Review> Blist(HttpServletRequest req);
	
	

}

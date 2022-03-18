package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Profile;




public interface ProfileDao {
	

	
	public Profile selectProfile(Connection conn , Profile profile);
	
	
	public int updateProfile(Connection conn , Profile profile);
	

}



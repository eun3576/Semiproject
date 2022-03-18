package dto;

import javax.servlet.http.HttpServletRequest;

import util.Paging;

public class Profile {
	private int user_no;
	private String id;
	private String password;
	private String nickname;
	private String sympton;
	private String phonenumber;

	
	@Override
	public String toString() {
		return "Profile [user_no=" + user_no + ", id=" + id + ", password=" + password + ", nickname=" + nickname
				+ ", sympton=" + sympton + ", phonenumber=" + phonenumber + "]";
	}


	public int getUser_no() {
		return user_no;
	}


	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getSympton() {
		return sympton;
	}


	public void setSympton(String sympton) {
		this.sympton = sympton;
	}


	public String getPhonenumber() {
		return phonenumber;
	}


	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


	public static Paging getPaging(HttpServletRequest req) {
		
		return null;
	}




	
	
	
	
}

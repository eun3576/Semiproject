package dto;

public class UserInfo {
	private int userNo;
    private String id;
    private String password;
    private String gender;
    private String nickname;
    private String sympton;
    private int phonenumber;
    
    //toString
	@Override
	public String toString() {
		return "UserInfo [userNo=" + userNo + ", id=" + id + ", password=" + password + ", gender=" + gender
				+ ", nickname=" + nickname + ", sympton=" + sympton + ", phonenumber=" + phonenumber + "]";
	} 
	

	//getter and setters
	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public int getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}
    
	
    
}

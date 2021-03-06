package dto;

public class UserInfo {
	private int userNo;
    private String id;
    private String password;
    private String gender;
    private String nickname;
    private String symptom;
    private String phonenumber;
    
    //toString
	@Override
	public String toString() {
		return "UserInfo [userNo=" + userNo + ", id=" + id + ", password=" + password + ", gender=" + gender
				+ ", nickname=" + nickname + ", symptom=" + symptom + ", phonenumber=" + phonenumber + "]";
	}
	
	//setter, getter
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
	public String getSymptom() {
		return symptom;
	}
	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
    
}

package dto;

public class Manager {

	private int manager_no;
	private String id;
	private String password;
	
	@Override
	public String toString() {
		return "Manager [manager_no=" + manager_no + ", id=" + id + ", password=" + password + "]";
	}
	public int getManager_no() {
		return manager_no;
	}
	public void setManager_no(int manager_no) {
		this.manager_no = manager_no;
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
	
	
}

package atm_classes;

public class Admin {

	private String login_id;
	private String password;
	
	Admin(String login_id, String password){
		this.login_id = login_id;
		this.password = password;
	}
	
	Admin(){}
	
	public void set_login_id(String login_id) {
		this.login_id = login_id;
	}
	
	public void set_password(String password) {
		this.password = password;
	}
	
	public String get_login_id() {
		return login_id;
	}
	
	public String get_password() {
		return password;
	}
}



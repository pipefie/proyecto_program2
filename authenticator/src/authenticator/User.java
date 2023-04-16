package authenticator;

public class User {
	private String name;
	private String password;
	Authentication authentication_method;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Authentication getAuthentication_method() {
		return authentication_method;
	}
	public void setAuthentication_method(Authentication authentication_method) {
		this.authentication_method = authentication_method;
	}
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String name, String password, Authentication authentication_method) {
		super();
		this.name = name;
		this.password = password;
		this.authentication_method = authentication_method;
	}
	
	
	
	

}

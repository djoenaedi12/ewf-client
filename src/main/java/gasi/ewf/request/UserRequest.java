package gasi.ewf.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

public class UserRequest {

	private String id;

	@NotBlank(message = "Please provide a name")
    private String name;

	@NotBlank(message = "Please provide a username")
    private String username;

	@NotBlank(message = "Please provide a email")
    private String email;

	@NotBlank(message = "Please provide a password")
    //@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@!^%*?&_\-)(></\\|{}[\]"',.~`+=;:#])[A-Za-z\d$@!^%*?&_\-)(></\\|{}[\]"',.~`+=;:#]+")
    private String password;
    
	@NotBlank(message = "Please provide a role")
    private List<String> role = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}
    
}

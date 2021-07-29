package br.com.vacine_se.admin;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;

public class Admin {
	private int id;
	@NotNull(message = "Admin name required")
	@Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+$", message = "Admin name must be a string")
    private final String name;
	@NotNull(message = "Admin username is required")
	@Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Admin username must be letters and/or numbers")
    private final String userName;
	@NotNull(message = "Admin password is required")
    private final String password;
	@NotNull(message = "Admin email is required")
    @Email(message = "must be a valid email format")
    private final String email;
    
    
	public Admin(String name, String userName, 
			String password, String email) {
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	
	@Id
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}
    
}

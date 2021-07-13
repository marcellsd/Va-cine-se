package br.com.vacine_se.admin;

import org.springframework.data.annotation.Id;

public class Admin {
	private final Long id;
    private final String name;
    private final String userName;
    private final String password;
    private final String email;
    
    
	public Admin(Long id, String name, String userName, 
			String password, String email) {
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	
	@Id
	public Long getId() {
		return id;
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


	
	
    public Admin updateWith(Admin user) {
        return new Admin(
           this.id,
           user.name,
           user.userName,
           user.password,
           user.email
        );
    }
    
}

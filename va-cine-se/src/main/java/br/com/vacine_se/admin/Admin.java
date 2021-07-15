package br.com.vacine_se.admin;

import org.springframework.data.annotation.Id;

import br.com.vacine_se.utils.IdGenerator;

public class Admin {
	private String id;
    private final String name;
    private final String userName;
    private final String password;
    private final String email;
    
    
	public Admin(String name, String userName, 
			String password, String email) {
		this.id = IdGenerator.getHash();
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	
	@Id
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
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
	
    public Admin updateWith(Admin user) {
        return new Admin(
           user.name,
           user.userName,
           user.password,
           user.email
        );
    }
    
}

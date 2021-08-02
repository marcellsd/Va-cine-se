package br.com.vacine_se.user;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import org.springframework.data.annotation.Id;

public class User {
	private int id;
	@NotNull(message = "name required")
	@Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+$", message = "name must be a string")
    private String name;
	@NotNull(message = "age is required")
    @Positive(message = "age must be positive")
    private int age;
	@NotNull(message = "districtId is required")
    @Positive(message = "districtId must be positive")
    private int districtId;
	@NotNull(message = "comorbidity status is required")
    private boolean comorbidity;
	@NotNull(message = "cpf is required")
	@Pattern(regexp = "([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}-[0-9]{2})", message = "must be a valid cpf format")
	private String cpf;
    @NotNull(message = "username is required")
	@Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "username must be letters and/or numbers")
    private String userName;
    @NotNull(message = "password is required")
    private String password;
    @NotNull(message = "email is required")
    @Email(message = "must be a valid email format")
    private String email;
    @NotNull(message = "Phone number is required")
   	@Pattern(regexp = "^[0-9 ]+$", message = "phone must be numbers")
    private String phoneNumber;
    private int schedulingId;
    boolean firstDose = false;
    boolean secondDose = false;
    
	public User(String name, int age, int districtId, boolean comorbidity, String cpf,
			String userName, String password, String email, String phoneNumber, int schedulingId) {
		this.name = name;
		this.age = age;
		this.districtId = districtId;
		this.comorbidity = comorbidity;
		this.cpf = cpf;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.schedulingId = schedulingId;
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
	public int getAge() {
		return age;
	}

	public int getDistrictId() {
		return districtId;
	}

	public boolean isComorbidity() {
		return comorbidity;
	}

	public String getCpf() {
		return cpf;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public int getSchedulingId() {
		return schedulingId;
	}

	public void setSchedulingId(int schedulingId) {
		this.schedulingId = schedulingId;
		
	}

	public boolean hasFirstDose() {
		return firstDose;
	}

	public void setFirstDose(boolean firstDose) {
		this.firstDose = firstDose;
	}

	public boolean hasSecondDose() {
		return secondDose;
	}

	public void setSecondDose(boolean secondDose) {
		this.secondDose = secondDose;
	}
	
	
   
}

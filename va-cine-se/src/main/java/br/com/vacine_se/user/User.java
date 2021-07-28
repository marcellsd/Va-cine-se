package br.com.vacine_se.user;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;

public class User {
	private int id;
	@NotNull(message = "name required")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "name must be a string")
    private String name;
	@NotNull(message = "age is required")
    @Positive(message = "age must be positive")
    private int age;
	@NotNull(message = "districtId is required")
    @Positive(message = "districtId must be positive")
    private Long districtId;
	@NotNull(message = "comorbidity status is required")
    private boolean comorbidity;
	@NotNull(message = "cpf is required")
	@CPF(message = "must be a valid cpf format")
    private String cpf;
    private LocalDate dateScheduled;
    @NotNull(message = "username is required")
	@Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "username must be letters and/or numbers")
    private String userName;
    @NotNull(message = "password is required")
    private String password;
    @NotNull(message = "email is required")
    @Email(message = "must be a valid email format")
    private String email;
    @NotNull(message = "Phone number is required")
   	@Pattern(regexp = "^[0-9 ]+$", message = "username must be numbers")
    private String phoneNumber;
    private Long schedulingId;
    
	public User(String name, int age, Long districtId, boolean comorbidity, String cpf, LocalDate dateScheduled,
			String userName, String password, String email, String phoneNumber, Long schedulingId) {
		this.name = name;
		this.age = age;
		this.districtId = districtId;
		this.comorbidity = comorbidity;
		this.cpf = cpf;
		this.dateScheduled = dateScheduled;
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

	public Long getDistrictId() {
		return districtId;
	}

	public boolean isComorbidity() {
		return comorbidity;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDateScheduled() {
		return dateScheduled;
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

	public Long getSchedulingId() {
		return schedulingId;
	}
   
}

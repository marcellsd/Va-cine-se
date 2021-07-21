package br.com.vacine_se.user;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;

import br.com.vacine_se.utils.IdGenerator;

public class User {
	private String id;
	@NotNull(message = "name required")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "name must be a string")
    private final String name;
	@NotNull(message = "age is required")
    @Positive(message = "age must be positive")
    private final int age;
	@NotNull(message = "districtId is required")
    @Positive(message = "districtId must be positive")
    private final Long districtId;
	@NotNull(message = "comorbidity status is required")
    private final boolean comorbidity;
	@NotNull(message = "cpf is required")
	@CPF(message = "must be a valid cpf format")
    private final String cpf;
    private final LocalDate dateScheduled;
    @NotNull(message = "username is required")
	@Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "username must be letters and/or numbers")
    private final String userName;
    @NotNull(message = "password is required")
    private final String password;
    @NotNull(message = "email is required")
    @Email(message = "must be a valid email format")
    private final String email;
    @NotNull(message = "Phone number is required")
   	@Pattern(regexp = "^[0-9 ]+$", message = "username must be numbers")
    private final String phoneNumber;
    private final Long schedulingId;
    
	public User(String name, int age, Long districtId, boolean comorbidity, String cpf, LocalDate dateScheduled,
			String userName, String password, String email, String phoneNumber, Long schedulingId) {
		this.id = IdGenerator.getHash();
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
	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	
	
    public User updateWith(User user) {
        return new User(
			// this.id,
           	user.name,
           	user.age,
           	user.districtId,
           	user.comorbidity,
           	user.cpf,
           	user.dateScheduled,
           	user.userName,
           	user.password,
           	user.email,
           	user.phoneNumber,
           	user.schedulingId
        );
    }
    
}

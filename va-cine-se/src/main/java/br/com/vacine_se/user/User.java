package br.com.vacine_se.user;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import br.com.vacine_se.utils.IdGenerator;

public class User {
	private String id;
    private final String name;
    private final int age;
    private final Long districtId;
    private final boolean comorbidity;
    private final String cpf;
    private final LocalDate dateScheduled;
    private final String userName;
    private final String password;
    private final String email;
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

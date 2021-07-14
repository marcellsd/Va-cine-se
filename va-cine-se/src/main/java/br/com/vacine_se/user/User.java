package br.com.vacine_se.user;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

public class User {
	private Long id;
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
    
	public User(Long id, String name, int age, Long districtId, boolean comorbidity, String cpf, LocalDate dateScheduled,
			String userName, String password, String email, String phoneNumber, Long schedulingId) {
		this.id = id;
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

	public User(User user) {
		this.id = user.id;
		this.name = user.name;
		this.age = user.age;
		this.districtId = user.districtId;
		this.comorbidity = user.comorbidity;
		this.cpf = user.cpf;
		this.dateScheduled = user.dateScheduled;
		this.userName = user.userName;
		this.password = user.password;
		this.email = user.email;
		this.phoneNumber = user.phoneNumber;
		this.schedulingId = user.schedulingId;
	}
	
	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
           this.id,
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

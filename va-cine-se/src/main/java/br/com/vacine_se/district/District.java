package br.com.vacine_se.district;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.Id;

public class District {
	@NotNull(message = "must be a District ID")
	@Positive(message="ID must be an positive integer")
	private int id;
	@NotNull(message = "must be a district name")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "name must be a string")
	private final String name;
	
	public District(int id, String name){
		this.id = id;
		this.name = name;
	}
    @Id
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
}

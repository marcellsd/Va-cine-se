package br.com.vacine_se.district;
import org.springframework.data.annotation.Id;

public class District {
	private final Long id;
	private final String name;
	
	public District(Long id, String name){
		this.id = id;
		this.name = name;
	}
    @Id
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
}

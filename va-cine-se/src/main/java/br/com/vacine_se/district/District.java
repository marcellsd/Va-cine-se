package br.com.vacine_se.district;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;


import org.springframework.data.annotation.Id;

import br.com.vacine_se.utils.Coordinate;


public class District {
	@NotNull(message = "must be a District ID")
	@Positive(message="ID must be an positive integer")
	private int id;
	@NotNull(message = "must be a district name")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "name must be a string")
	private final String name;
	private Coordinate<Integer, Integer> coordinate;
	
	public District(String name, Integer coordX, Integer coordY){
		this.name = name;
		this.coordinate = new Coordinate<Integer, Integer>(coordX,coordY);
	}
    @Id
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

	public Integer getCoordnateX() {
		return this.coordinate.getX();
	}
	public Integer getCoordnateY() {
		return this.coordinate.getY();
	public void setId(int id) {
		this.id = id;
	}
}

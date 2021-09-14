package br.com.vacine_se.district;

import org.springframework.data.annotation.Id;

import br.com.vacine_se.utils.Coordinate;


public class District {

	private int id;
	private final String name;
	private Coordinate<Integer, Integer> coordinate;
	private int population;
	
	public District(String name, Integer coordX, Integer coordY){
		this.name = name;
		this.coordinate = new Coordinate<Integer, Integer>(coordX,coordY);
	}
	public District(String name, Integer coordX, Integer coordY, int population){
		this.name = name;
		this.coordinate = new Coordinate<Integer, Integer>(coordX,coordY);
		this.population = population;
	}

    public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
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
	}

	public void setId(int id) {
		this.id = id;
	}
}

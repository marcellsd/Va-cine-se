package br.com.vacine_se.utils;

public class Coordinate<X, Y> { 
	  public final X x; 
	  public final Y y; 
	  
	  public Coordinate(X x, Y y) { 
	    this.x = x; 
	    this.y = y; 
	  }

	public X getX() {
		return x;
	}

	public Y getY() {
		return y;
	}
	  
} 


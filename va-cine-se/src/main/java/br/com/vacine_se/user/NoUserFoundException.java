package br.com.vacine_se.user;

import java.util.NoSuchElementException;

public class NoUserFoundException extends NoSuchElementException {

	@Override
	public String getMessage() {
		return "User not found";
	}
	
}

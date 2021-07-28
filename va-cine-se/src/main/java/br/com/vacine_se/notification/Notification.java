package br.com.vacine_se.notification;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class Notification {
	private int id;
	@NotNull(message = "must be a user ID")
	private final int userId;
	@NotNull(message = "must be a notification content")
	private final String content;
	
	public Notification(int userId, String content){
		this.userId = userId;
		this.content = content;
	}
    @Id
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public int getUserId() {
		return this.userId;
	}
}

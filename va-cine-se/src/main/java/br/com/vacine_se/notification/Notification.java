package br.com.vacine_se.notification;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import br.com.vacine_se.utils.IdGenerator;

public class Notification {
	private String id;
	@NotNull(message = "must be a user ID")
	private final String userId;
	@NotNull(message = "must be a notification content")
	private final String content;
	
	public Notification(String userId, String content){
		this.id = IdGenerator.getHash();
		this.userId = userId;
		this.content = content;
	}
    @Id
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public Notification updateWith(Notification district) {
		return new Notification(district.getUserId(), district.getContent());
	}
}

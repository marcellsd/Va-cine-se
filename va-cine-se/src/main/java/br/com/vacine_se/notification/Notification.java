package br.com.vacine_se.notification;
import org.springframework.data.annotation.Id;

public class Notification {
	private final Long id;
	private final Long userId;
	private final String content;
	
	public Notification(Long id, Long districtId, String content){
		this.id = id;
		this.userId = districtId;
		this.content = content;
	}
    @Id
	public Long getId() {
		return this.id;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	
	public Notification updateWith(Notification district) {
		return new Notification(this.id, district.getUserId(), district.getContent());
	}
}

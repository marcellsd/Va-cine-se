package br.com.vacine_se.notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {

	private NotificationRepository repository;
	
	private static List<Notification> getDefaultNotification(){
		return List.of(
				new Notification(1, "Sua vacinação está próxima. Verifique o dia e o local no app!")
				);
	}
	
	public NotificationService(NotificationRepository repository) {
		this.repository = repository;
		for (Notification not : getDefaultNotification()) {
			this.repository.save(not);
		}
	}
	
	
	public List<Notification> findAll() {
        List<Notification> list = new ArrayList<>();
        Iterable<Notification> notification = repository.getAll();
        notification.forEach(list::add);
        return list;
    }
	public Optional<Notification> find(int id) {
		return repository.getById(id);
	}
	
	public Notification create(Notification notification) {
		return repository.save(notification);
	}
	
	public Notification update(int id, Notification newNotification) {
		Notification notification = this.find(id).orElseThrow();
		newNotification.setId(notification.getId());
		return repository.update(newNotification);
	}

	public void delete(int id) {
		Notification notification = this.find(id).orElseThrow();
		repository.delete(notification);
	}
}


package br.com.vacine_se.notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
@EnableMapRepositories
public class NotificationService {
	private final CrudRepository<Notification, Long> repository;
	
	public NotificationService(CrudRepository<Notification, Long> repository) {
		this.repository = repository;
		this.repository.saveAll(getDefaultNotification());
	}
	
	
	private static List<Notification> getDefaultNotification(){
		return List.of(
				new Notification(0L, 0L, "Sua vacinação está próxima. Verifique o dia e o local no app!")
				);
	}
	
	public List<Notification> findAll() {
        List<Notification> list = new ArrayList<>();
        Iterable<Notification> notification = repository.findAll();
        notification.forEach(list::add);
        return list;
    }
	public Optional<Notification> find(Long id) {
		return repository.findById(id);
	}
	
	public Notification create(Notification notification) {
		Notification copy = new Notification(notification.getId(), notification.getUserId(), notification.getContent());
		return repository.save(copy);
	}
	
	public Optional<Notification> update( Long id, Notification newNotification) {
        return repository.findById(id)
                .map(oldNotification -> {
                	Notification updated = oldNotification.updateWith(newNotification);
                   return repository.save(updated);
                });
    }
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}


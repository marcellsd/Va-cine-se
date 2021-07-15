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
	private final CrudRepository<Notification, String> repository;
	
	public NotificationService(CrudRepository<Notification, String> repository) {
		this.repository = repository;
		// this.repository.saveAll(getDefaultNotification());
	}
	
	
	// TODO: Adicionar user service e buscar por um id de usuario para criar a notificacao
	// private static List<Notification> getDefaultNotification(){
	// 	return List.of(
	// 			new Notification(0L, "Sua vacinação está próxima. Verifique o dia e o local no app!")
	// 			);
	// }
	
	public List<Notification> findAll() {
        List<Notification> list = new ArrayList<>();
        Iterable<Notification> notification = repository.findAll();
        notification.forEach(list::add);
        return list;
    }
	public Optional<Notification> find(String id) {
		return repository.findById(id);
	}
	
	public Notification create(Notification notification) {
		Notification copy = new Notification(notification.getUserId(), notification.getContent());
		return repository.save(copy);
	}
	
	public Notification update( String id, Notification newNotification) {
        newNotification.setId(id);
		return newNotification;
	}
	/*
	public Optional<Notification> update( String id, Notification newNotification) {
        return repository.findById(id)
                .map(oldNotification -> {
                	Notification updated = oldNotification.updateWith(newNotification);
                   return repository.save(updated);
                });
    }
	*/
	public void delete(String id) {
		repository.deleteById(id);
	}
}


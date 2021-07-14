package br.com.vacine_se.notification;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InMemoryNotificationRepository extends CrudRepository<Notification, Long>{}


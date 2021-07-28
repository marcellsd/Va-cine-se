package br.com.vacine_se.notification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import br.com.vacine_se.InMemoryRepository;

@Repository
public class NotificationRepository implements InMemoryRepository<Notification> {

    private List<Notification> notifications = new ArrayList<>();

    @Override
    public Optional<Notification> getById(int id) {
        return Optional.ofNullable(notifications.get(id));
    }

    @Override
    public Collection<Notification> getAll() {
        return notifications.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public Notification save(Notification notification) {
        notifications.add(notification);
        int index = notifications.size() - 1;
        notification.setId(index);
        return notification;
    }

    @Override
    public Notification update(Notification notification) {
        return notifications.set(notification.getId(), notification);
    }

    @Override
    public void delete(Notification notification) {
        notifications.set(notification.getId(), null);
    }
    
}

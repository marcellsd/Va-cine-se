package br.com.vacine_se.notification;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    private final NotificationService service;
    
    NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping("/notifications")
    Iterable<Notification> all() {
        return service.findAll();
    }

    @GetMapping("/notifications/{id}")
    Notification one(@PathVariable String id) {
        return service.find(id).orElseThrow();
    }

    @PostMapping("/notifications")
    Notification newNotification(@RequestBody Notification notification) {
        return service.create(notification);
    }

    @PutMapping("/notifications/{id}")
    Notification updateNotification(@RequestBody Notification notification, @PathVariable String id) {
        return service.update(id, notification);
    }

    @DeleteMapping("/notifications/{id}")
    void deleteNotification(@PathVariable String id) {
        service.delete(id);
    }
    
    
}

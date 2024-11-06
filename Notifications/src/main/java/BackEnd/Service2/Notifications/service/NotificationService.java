package BackEnd.Service2.Notifications.service;

import BackEnd.Service2.Notifications.repository.NotificationRepository;
import BackEnd.Service2.Notifications.model.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;

    public void createNotification(Notification notification){
        repository.save(notification);
    }
}

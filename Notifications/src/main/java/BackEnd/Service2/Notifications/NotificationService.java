package BackEnd.Service2.Notifications;

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

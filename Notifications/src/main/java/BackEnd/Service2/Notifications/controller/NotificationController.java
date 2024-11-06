package BackEnd.Service2.Notifications.controller;

import BackEnd.Service2.Notifications.service.NotificationService;
import BackEnd.Service2.Notifications.model.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/employee-notification")
    public ResponseEntity<?> notifyEmployee(@RequestBody Long employeeNumber) {

        Notification notification = new Notification(employeeNumber,
                "the interested party has left the permitted radius or entered a prohibited area, please return to the company ",
                LocalDateTime.now());
        notificationService.createNotification(notification);
        return new ResponseEntity<>("Notification send to the employee", HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> sendPromotionNotifications(@RequestBody List<Long> phoneNumbers,
                                                        @RequestBody String promotionContent) {
        if (!phoneNumbers.isEmpty()) {
            phoneNumbers.forEach(
                    phoneNumber -> {
                        Notification notification = new Notification(phoneNumber,
                                promotionContent,
                                LocalDateTime.now());
                        notificationService.createNotification(notification);
                    });
            return new ResponseEntity<>("All notifications were send", HttpStatus.OK);
        }
        return new ResponseEntity<>("There isn't any phoneNumber to send notification", HttpStatus.NO_CONTENT);
    }

}

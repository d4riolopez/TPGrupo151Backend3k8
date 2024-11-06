package BackEnd.Service2.Notifications.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long number;

    private String notificationContent;

    private LocalDateTime dateTime;

    public Notification(Long phoneNumber, String notificationContent, LocalDateTime dateTime) {
        this.number = phoneNumber;
        this.notificationContent = notificationContent;
        this.dateTime = dateTime;
    }

    public Notification() {

    }
}

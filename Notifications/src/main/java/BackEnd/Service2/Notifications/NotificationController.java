package BackEnd.Service2.Notifications;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    @PostMapping("/employee-notification")
    public ResponseEntity<?> notifyEmployee(@RequestBody Long employeeNumber) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

}

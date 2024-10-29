package UTN.FRC.sistemas.TPI.service;

import UTN.FRC.sistemas.TPI.exceptionHandling.exception.PositionNotFoundException;
import UTN.FRC.sistemas.TPI.model.entities.Position;
import UTN.FRC.sistemas.TPI.model.entities.Test;
import UTN.FRC.sistemas.TPI.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PositionService extends ServiceImp<Position, Long> {

    private final PositionRepository repository;
    private final String positionNotFoundMessage = "There isn't a position with that id:";
    private final TestService testService;
    private final WebClient webClient;

    @Autowired
    public PositionService(WebClient.Builder builder, PositionRepository positionRepository, TestService testService) {
        this.repository = positionRepository;
        this.testService = testService;
        this.webClient = builder.baseUrl("http://localhost:8081").build();
    }

    public Position calculatePosition(Test test, Long latitude, Long length) {
        Position position = new Position();
        position.setLatitude(latitude);
        position.setLength(length);
        position.setVehicle(test.getVehicle());
        position.setDateTime(LocalDateTime.now());
        create(position);

        if (validatePosition(latitude, length)) {
            //restricting the interest of the current test
            test.getInterested().setRestricted(true);
            test.setIncident(true);
            testService.update(test);

            //sending the notification to the employee of the test
            sendNotification(test.getEmployee().getContactNumber());
        }
        return position;
    }


    @Override
    public void create(Position entity) {
        repository.save(entity);
    }

    @Override
    public Position findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PositionNotFoundException(positionNotFoundMessage + id));
    }

    @Override
    public List<Position> getAll() {
        return repository.findAll();
    }

    @Override
    public Position update(Position entity) {
        if (existsById(entity.getId())) {
            return repository.save(entity);
        } else {
            throw new PositionNotFoundException(positionNotFoundMessage + entity.getId());
        }

    }

    @Override
    public void delete(Long id) {
        Position pos = findById(id);
        repository.delete(pos);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    private void sendNotification(Long employeeNumber) {
        webClient.post()
                .uri("/api/v1/notification/employee-notification")
                .bodyValue(employeeNumber)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(success -> System.out.println("Notification send successfully to the employee"))
                .subscribe();

    }

    private boolean validatePosition(Long latitude, Long length) {
        //needs to be implemented
        return false;
    }
}

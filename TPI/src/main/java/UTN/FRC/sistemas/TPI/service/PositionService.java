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
////acaaaaaaaaaaaaaaaaa
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

    public void damePociones(){
        //pedirme a la api de la uv
        //procesar esas ubicaciones
        //agregar en una lista nuestra donde no se puede ir
            //list<>
            //radio valido
    }

    private boolean validatePosition(Long latitude, Long length) {
        //creamos posicion
        //return (esta dentro radio && !EstaZonaPRohibida)
        // needs to be implemented
        return false;
    }

    public boolean estaEnZonaProhibida(Position pos){
        return true;
        //if(aaa && bbb && ccc)
    }
    public double calculateDistanceEuclidean(Position a, Position b) {
        return Math.sqrt(
                Math.pow(2,
                        ((b.getLatitude() - a.getLatitude()))*111 ) +
                        Math.pow(2,
                                (b.getLength() - a.getLength())*111
                        ));
    }

    public void calculateRadio(){
        //Dos validaciones
        //1 si tiene menos de 5 km de distancia desde el centro
        //2(en partes)
        // medir desde el centro de cada zona peligrosa si la distancia hasta el punto es
        // menor igual a su radio
    }


}

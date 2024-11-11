package UTN.FRC.sistemas.TPI.service;

import UTN.FRC.sistemas.TPI.exceptionHandling.exception.PositionNotFoundException;
import UTN.FRC.sistemas.TPI.model.entities.Position;
import UTN.FRC.sistemas.TPI.model.entities.Test;
import UTN.FRC.sistemas.TPI.repository.PositionRepository;
import UTN.FRC.sistemas.TPI.utils.API;
import UTN.FRC.sistemas.TPI.utils.Posicion;
import UTN.FRC.sistemas.TPI.utils.ZonaPeligrosa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PositionService extends ServiceImp<Position, Long> {

    private final PositionRepository repository;
    private final String positionNotFoundMessage = "There isn't a position with that id:";
    private final TestService testService;
    private final WebClient webClient;
    private final WebClient apiUTN;

    @Autowired
    public PositionService(WebClient.Builder builder, PositionRepository positionRepository, TestService testService) {
        this.repository = positionRepository;
        this.testService = testService;
        this.webClient = builder.baseUrl("http://localhost:8081").build();
        this.apiUTN = builder.baseUrl("https://labsys.frc.utn.edu.ar/apps-disponibilizadas/backend").build();
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
        System.out.println("notification sended");
    }

    public boolean validatePosition(Long latitude, Long length) {
        //creamos posicion
        Posicion posicion = new Posicion(latitude, length);

        API api = apiUTN.get()
                .uri("/api/v1/configuracion/")
                .retrieve()
                .bodyToMono(API.class)
                .block();
        System.out.println(api.toString());


        if (calculateDistanceEuclidean(posicion, api.getCoordenadasAgencia()) < api.getRadioAdmitidoKm()){
            for(ZonaPeligrosa zonaPeligrosa : api.getZonasRestringidas()){
                if(estaEnZonaProhibida(zonaPeligrosa, posicion)){
                    System.out.println("Está en zona peligrosa");
                    return false;
                }
            }
            System.out.println("La posición es válida");
            return true;
        }
        System.out.println("Está afuera del rango");
        return false;
    }

    public Posicion calcularCentroDeZona(Posicion pos1, Posicion pos2) {
        double centroX = (pos1.getLon() + pos2.getLon())/2;
        double centroY = (pos1.getLat() + pos2.getLat())/2;

        Posicion centro = new Posicion(centroX, centroY);

        return centro;
    }

    public boolean estaEnZonaProhibida(ZonaPeligrosa zonaPeligrosa, Posicion pos){
        Posicion centro = calcularCentroDeZona(zonaPeligrosa.getNoroeste(), zonaPeligrosa.getSureste());

        double radio = calculateDistanceEuclidean(zonaPeligrosa.getNoroeste(), zonaPeligrosa.getSureste()) / 2f;

        return calculateDistanceEuclidean(centro, pos) < radio;
    }
    public double calculateDistanceEuclidean(Posicion a, Posicion b) {
        return Math.sqrt(
                Math.pow(2,
                        ((b.getLat() - a.getLat()))*111 ) +
                        Math.pow(2,
                                (b.getLon() - a.getLon())*111
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

package UTN.FRC.sistemas.TPI.service;

import UTN.FRC.sistemas.TPI.exceptionHandling.exception.Interested.InterestedIsRestrictedException;
import UTN.FRC.sistemas.TPI.exceptionHandling.exception.Interested.InterestedLicenseExpiredException;
import UTN.FRC.sistemas.TPI.exceptionHandling.exception.TestNotFoundException;
import UTN.FRC.sistemas.TPI.exceptionHandling.exception.Vehicle.VehicleAlreadyTestingException;
import UTN.FRC.sistemas.TPI.model.entities.Interested;
import UTN.FRC.sistemas.TPI.model.entities.Test;
import UTN.FRC.sistemas.TPI.model.entities.Vehicle;
import UTN.FRC.sistemas.TPI.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TestService extends ServiceImp<Test, Long> {
    private final TestRepository repository;
    private String notFoundMessage = "There isn't a test with that id:";

    @Override
    public void create(Test test) {
        //Crear una nueva prueba,
        // Vamos a asumir que un interesado puede tener una única licencia registrada en el sistema y
        //que todos los vehículos están patentados.

        Interested interested = test.getInterested();
        //ni que esté restringido para probar vehículos en la agencia.
        if (!interested.getRestricted()) {
            //validando que el cliente no tenga la licencia vencida
            if (interested.isLicenceActive()) {
                Vehicle vehicle = test.getVehicle();
                //validar que un mismo vehículo no esté siendo probado en ese mismo momento.
                if (!vehicle.isOnTesting()) {
                    vehicle.setOnTesting(true);
                    repository.save(test);
                }else
                    throw  new VehicleAlreadyTestingException("The current vehicle is in a drive test already");
            } else
                throw new InterestedLicenseExpiredException("The license of the interested party has expired");
        } else
            throw new InterestedIsRestrictedException("The current interested is restricted in the company");
    }

    @Override
    public Test findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TestNotFoundException(notFoundMessage + id));
    }

    @Override
    public List<Test> getAll() {
        return repository.findAll();
    }

    @Override
    public Test update(Test entity) {
        if (existsById(entity.getId())) {
            return repository.save(entity);
        } else {
            throw new TestNotFoundException(notFoundMessage + entity.getId());
        }
    }

    @Override
    public void delete(Long id) {
        Test test = findById(id);
        repository.delete(test);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}

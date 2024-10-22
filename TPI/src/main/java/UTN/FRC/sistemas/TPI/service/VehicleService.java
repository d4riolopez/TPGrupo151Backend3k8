package UTN.FRC.sistemas.TPI.service;

import UTN.FRC.sistemas.TPI.exceptionHandling.exception.VehicleNotFoundException;
import UTN.FRC.sistemas.TPI.model.entities.Vehicle;
import UTN.FRC.sistemas.TPI.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.spi.ServiceRegistry;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService extends ServiceImp<Vehicle, Long> {
    private String notFoundMessage = "There isn't a Vehicle with that id:";
    private final VehicleRepository repository;

    @Override
    public void create(Vehicle entity) {
        repository.save(entity);
    }

    @Override
    public Vehicle findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(notFoundMessage + id));
    }

    @Override
    public List<Vehicle> getAll() {
        return repository.findAll();
    }

    @Override
    public Vehicle update(Vehicle entity) {
        if(existsById(entity.getId())){
            return repository.save(entity);
        }else{
            throw  new VehicleNotFoundException(notFoundMessage + entity.getId());
        }
    }

    @Override
    public void delete(Long id) {
        Vehicle vehicle = findById(id);
        repository.delete(vehicle);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}

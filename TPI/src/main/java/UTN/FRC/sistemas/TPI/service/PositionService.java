package UTN.FRC.sistemas.TPI.service;

import UTN.FRC.sistemas.TPI.exceptionHandling.exception.PositionNotFoundException;
import UTN.FRC.sistemas.TPI.model.entities.Position;
import UTN.FRC.sistemas.TPI.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService extends ServiceImp<Position, Long>{
    private final PositionRepository repository;
    private String positionNotFoundMessage = "There isn't a position with that id:";

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
        if(existsById(entity.getId())){
            return repository.save(entity);
        }else{
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
}

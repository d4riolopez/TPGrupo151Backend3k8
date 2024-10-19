package UTN.FRC.sistemas.TPI.service;

import UTN.FRC.sistemas.TPI.exceptionHandling.exception.ModelNotFoundException;
import UTN.FRC.sistemas.TPI.model.entities.Model;
import UTN.FRC.sistemas.TPI.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelService extends ServiceImp<Model, Long> {

    private final ModelRepository repository;
    private String notFoundMessage = "There isn't a model with that id:";

    @Override
    public void create(Model entity) {
        repository.save(entity);
    }

    @Override
    public Model findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(notFoundMessage + id));
    }

    @Override
    public List<Model> getAll() {
        return repository.findAll();
    }

    @Override
    public Model update(Model entity) {
        if(repository.existsById(entity.getId())){
            return repository.save(entity);
        }else{
            throw new ModelNotFoundException(notFoundMessage +  entity.getId());
        }
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}

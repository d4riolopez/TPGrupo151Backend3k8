package UTN.FRC.sistemas.TPI.service;

import UTN.FRC.sistemas.TPI.exceptionHandling.exception.InterestedNotFoundException;
import UTN.FRC.sistemas.TPI.model.entities.Interested;
import UTN.FRC.sistemas.TPI.repository.InterestedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestedService extends ServiceImp<Interested, Long> {
    private final InterestedRepository repository;
    private String notFoundMessage = "There isn't an interested customer with that id:";

    @Override
    public void create(Interested entity) {
        repository.save(entity);
    }

    @Override
    public Interested findById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new  + id));
    }

    @Override
    public List<Interested> getAll() {
        return repository.findAll();
    }

    @Override
    public Interested update(Interested entity) {
        if(existsById(entity.getId())){
            return repository.save(entity);
        }else{
            throw new InterestedNotFoundException(notFoundMessage + entity.getId());
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

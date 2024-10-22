package UTN.FRC.sistemas.TPI.service;

import UTN.FRC.sistemas.TPI.exceptionHandling.exception.TestNotFoundException;
import UTN.FRC.sistemas.TPI.model.entities.Test;
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
    public void create(Test entity) {
        repository.save(entity);
    }

    @Override
    public Test findById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new TestNotFoundException(notFoundMessage + id));
    }

    @Override
    public List<Test> getAll() {
        return repository.findAll();
    }

    @Override
    public Test update(Test entity) {
        if(existsById(entity.getId())){
            return repository.save(entity);
        }else {
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

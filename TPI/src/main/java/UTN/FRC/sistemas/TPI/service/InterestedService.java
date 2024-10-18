package UTN.FRC.sistemas.TPI.service;

import UTN.FRC.sistemas.TPI.model.entities.Interested;
import UTN.FRC.sistemas.TPI.repository.InterestedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestedService extends ServiceImp<Interested, Long> {
    private final InterestedRepository repository;

    @Override
    public void create(Interested entity) {

    }

    @Override
    public Interested findById(Long aLong) {
        return null;
    }

    @Override
    public List<Interested> getAll() {
        return List.of();
    }

    @Override
    public Interested update(Interested entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }
}

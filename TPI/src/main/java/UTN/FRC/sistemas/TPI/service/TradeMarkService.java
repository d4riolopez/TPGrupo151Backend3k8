package UTN.FRC.sistemas.TPI.service;

import UTN.FRC.sistemas.TPI.exceptionHandling.exception.TradeMarkNotFoundException;
import UTN.FRC.sistemas.TPI.model.entities.TradeMark;
import UTN.FRC.sistemas.TPI.repository.TradeMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TradeMarkService extends ServiceImp<TradeMark, Long> {
    private String notFoundMessage = "There isn't a Mark with that id:";
    private final TradeMarkRepository repository;

    @Override
    public void create(TradeMark entity) {
        repository.save(entity);
    }

    @Override
    public TradeMark findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TradeMarkNotFoundException(notFoundMessage + id));
    }

    @Override
    public List<TradeMark> getAll() {
        return repository.findAll();
    }

    @Override
    public TradeMark update(TradeMark entity) {
        if(existsById(entity.getId())){
            return repository.save(entity);
        }else{
            throw new TradeMarkNotFoundException(notFoundMessage + entity.getId());
        }
    }

    @Override
    public void delete(Long id) {
        TradeMark tradeMark = findById(id);
        repository.delete(tradeMark);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}

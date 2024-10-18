package UTN.FRC.sistemas.TPI.repository;

import UTN.FRC.sistemas.TPI.model.entities.TradeMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeMarkRepository extends JpaRepository<TradeMark, Long> {
}

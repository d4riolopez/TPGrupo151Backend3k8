package UTN.FRC.sistemas.TPI.repository;

import UTN.FRC.sistemas.TPI.model.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
}

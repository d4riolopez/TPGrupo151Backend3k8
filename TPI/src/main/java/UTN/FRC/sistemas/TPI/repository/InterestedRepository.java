package UTN.FRC.sistemas.TPI.repository;

import UTN.FRC.sistemas.TPI.model.entities.Interested;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestedRepository extends JpaRepository<Interested, Long> {
}

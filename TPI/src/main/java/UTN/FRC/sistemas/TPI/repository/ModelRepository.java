package UTN.FRC.sistemas.TPI.repository;

import UTN.FRC.sistemas.TPI.model.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
}

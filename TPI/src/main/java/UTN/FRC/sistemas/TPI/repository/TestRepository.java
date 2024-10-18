package UTN.FRC.sistemas.TPI.repository;

import UTN.FRC.sistemas.TPI.model.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}

package UTN.FRC.sistemas.TPI.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@Setter
@Entity
@Table(name = "test_drives")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startedDateTime;

    private LocalDateTime endedDateTime;

    private String comments;

    @ManyToOne
    @JoinColumn(name = "employee_file")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "interested_id")
    private Interested interested;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}

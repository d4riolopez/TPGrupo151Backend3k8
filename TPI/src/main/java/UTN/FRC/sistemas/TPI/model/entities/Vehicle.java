package UTN.FRC.sistemas.TPI.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patent;

    @OneToMany(mappedBy = "vehicle")
    private Set<Position> positions;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    private boolean onTesting;
}

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

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private Set<Position> positions;

    @OneToMany(mappedBy = "vehicle", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private Set<Test> tests;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id")
    private Model model;

    private boolean onTesting;
}

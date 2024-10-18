package UTN.FRC.sistemas.TPI.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class Model {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    private Set<Vehicle> vehicles;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "markId")
    private TradeMark mark;

}

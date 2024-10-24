package UTN.FRC.sistemas.TPI.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter@Setter
public class Employee {
    @Id
    private Long file;

    private String name;

    private String surname;

    private Long contactNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<Test> tests;
}

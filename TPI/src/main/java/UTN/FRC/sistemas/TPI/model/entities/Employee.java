package UTN.FRC.sistemas.TPI.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long file;

    private String name;

    private String surname;

    private Long contactNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<Test> tests;

    private boolean onTesting;

    public List<Test> getIncidents(){
        return tests.stream().filter(Test::isIncident).toList();
    }
}

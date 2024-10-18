package UTN.FRC.sistemas.TPI.model.entities;

import UTN.FRC.sistemas.TPI.model.entities.enumerated.DocumentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class Interested {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private DocumentType documentType;

    private String documentNumber;

    private String name;

    private String surname;

    private Boolean restricted ;

    private int licenceNumber;

    private LocalDate expirationLicenceDAte;

    @OneToMany(mappedBy = "interested")
    private Set<Test> tests;

    private boolean licenceActive(){
        return LocalDate.now().isBefore(expirationLicenceDAte);
    }

}

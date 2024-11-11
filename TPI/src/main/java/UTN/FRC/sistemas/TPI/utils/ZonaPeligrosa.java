package UTN.FRC.sistemas.TPI.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@AllArgsConstructor
@ToString
public class ZonaPeligrosa {
    private Posicion noroeste;
    private Posicion sureste;
}

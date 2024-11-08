package UTN.FRC.sistemas.TPI.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter@Setter
@ToString
public class API {
    private Posicion coordenadasAgencia;
    private int radioAdmitidoKm;
    private List<ZonaPeligrosa> zonasRestringidas;
}


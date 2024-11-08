package UTN.FRC.sistemas.TPI.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class API {
    private Posicion coordenadasAgencia;
    private int radioAdmitidoKm;
    private List<ZonaPeligrosa> zonaPeligrosa;
}


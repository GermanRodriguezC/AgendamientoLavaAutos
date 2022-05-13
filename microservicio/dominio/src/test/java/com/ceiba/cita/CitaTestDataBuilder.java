package com.ceiba.cita;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.SolicitudAgendar;

public class CitaTestDataBuilder {

    private static final String PLACA = "AAA111";
    private static final String FECHA = "";
    private static final String HORA = "";
    private Long id;
    private double valor;

    public Cita crear() {
        return Cita.crear(new SolicitudAgendar(PLACA, FECHA, HORA));
    }
}

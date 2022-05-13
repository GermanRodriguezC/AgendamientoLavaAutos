package com.ceiba.cita.controlador;

import com.ceiba.cita.comando.ComandoSolucitudCita;

public class ComandoSolicitudCitaTestDataBuilder {

    private static final String PLACA = "ABC123";
    private static final String FECHA = "12/05/2022";
    private static final String HORA = "13:00";

    public ComandoSolucitudCita build() {
        return new ComandoSolucitudCita(PLACA, FECHA, HORA);
    }
}

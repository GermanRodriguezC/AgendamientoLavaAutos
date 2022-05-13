package com.ceiba.cita.comando.fabrica;

import com.ceiba.cita.comando.ComandoSolucitudCita;
import com.ceiba.cita.modelo.entidad.SolicitudAgendar;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudAgendar {

    public SolicitudAgendar crear(ComandoSolucitudCita comandoSolucitudCita) {
        return new SolicitudAgendar(comandoSolucitudCita.getPlaca(), comandoSolucitudCita.getFecha(), comandoSolucitudCita.getHora());
    }

}

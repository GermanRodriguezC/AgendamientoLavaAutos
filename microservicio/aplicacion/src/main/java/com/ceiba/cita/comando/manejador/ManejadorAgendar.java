package com.ceiba.cita.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.cita.comando.ComandoSolucitudCita;
import com.ceiba.cita.comando.fabrica.FabricaSolicitudAgendar;
import com.ceiba.cita.servicio.ServicioAgendar;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorAgendar implements ManejadorComandoRespuesta<ComandoSolucitudCita, ComandoRespuesta<Long>> {

    private final FabricaSolicitudAgendar fabricaSolicitudAgendar;
    private final ServicioAgendar servicioAgendar;

    public ManejadorAgendar(FabricaSolicitudAgendar fabricaSolicitudAgendar, ServicioAgendar servicioAgendar) {
        this.fabricaSolicitudAgendar = fabricaSolicitudAgendar;
        this.servicioAgendar = servicioAgendar;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoSolucitudCita comandoSolucitudCita) {
        return new ComandoRespuesta<>(servicioAgendar.ejecutar(fabricaSolicitudAgendar.crear(comandoSolucitudCita)));
    }
}
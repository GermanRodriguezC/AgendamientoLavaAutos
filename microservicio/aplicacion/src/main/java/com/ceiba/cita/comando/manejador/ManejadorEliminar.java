package com.ceiba.cita.comando.manejador;


import com.ceiba.ComandoRespuesta;
import com.ceiba.cita.servicio.ServicioEliminar;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminar implements ManejadorComandoRespuesta<Long, ComandoRespuesta<Long>> {

    private final ServicioEliminar servicioEliminar;


    public ManejadorEliminar(ServicioEliminar servicioEliminar) {
        this.servicioEliminar = servicioEliminar;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(Long id) {
        return new ComandoRespuesta<>(this.servicioEliminar.ejecutar(id));
    }
}

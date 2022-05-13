package com.ceiba.cita.comando.manejador;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.servicio.ServicioActualizar;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizar {

    private final ServicioActualizar servicioActualizar;

    public ManejadorActualizar(ServicioActualizar servicioActualizar) {
        this.servicioActualizar = servicioActualizar;
    }

    public ResumenCitaDTO ejecutar(ResumenCitaDTO resumenCitaDTO) {
        return this.servicioActualizar.ejecutar(resumenCitaDTO);
    }
}

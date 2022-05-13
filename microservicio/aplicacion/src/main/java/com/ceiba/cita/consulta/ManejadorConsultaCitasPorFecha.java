package com.ceiba.cita.consulta;


import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.servicio.ServicioConsultar;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultaCitasPorFecha {

    private final ServicioConsultar servicioConsultar;

    public ManejadorConsultaCitasPorFecha(ServicioConsultar servicioConsultar) {
        this.servicioConsultar = servicioConsultar;
    }

    public List<ResumenCitaDTO> ejecutar(String fecha) {
        return servicioConsultar.ejecutar(fecha);
    }
}

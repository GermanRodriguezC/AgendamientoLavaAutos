package com.ceiba.cita.controlador;


import com.ceiba.cita.consulta.ManejadorConsultaCitasPorFecha;
import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cita")
@Tag(name = "Controlador para la consuta de citas")
public class ConsultaControladorCita {

    private final ManejadorConsultaCitasPorFecha manejadorConsultaCitasPorFecha;

    public ConsultaControladorCita(ManejadorConsultaCitasPorFecha manejadorConsultaCitasPorFecha) {
        this.manejadorConsultaCitasPorFecha = manejadorConsultaCitasPorFecha;
    }

    @GetMapping
    @Operation(summary = "Consultar", description = "Metodo Utilizado para consultar las citas del dia deseado")
    public List<ResumenCitaDTO> obtenerCitaPorFecha(@RequestParam(value = "fecha", required = false) String fecha) {
        return manejadorConsultaCitasPorFecha.ejecutar(fecha);
    }
}

package com.ceiba.cita.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.cita.comando.ComandoSolucitudCita;
import com.ceiba.cita.comando.manejador.ManejadorActualizar;
import com.ceiba.cita.comando.manejador.ManejadorAgendar;
import com.ceiba.cita.comando.manejador.ManejadorEliminar;
import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cita")
@Tag(name = "Controlador comando cita")
public class ComandoControladorCita {

    private final ManejadorAgendar manejadorAgendar;
    private final ManejadorActualizar manejadorActualizar;
    private final ManejadorEliminar manejadorEliminar;

    public ComandoControladorCita(ManejadorAgendar manejadorAgendar, ManejadorActualizar manejadorActualizar, ManejadorEliminar manejadorEliminar) {
        this.manejadorAgendar = manejadorAgendar;
        this.manejadorActualizar = manejadorActualizar;
        this.manejadorEliminar = manejadorEliminar;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Agendar", description = "Metodo utilizado para agendar una nueva cita")
    public ComandoRespuesta<Long> agendar(@RequestBody ComandoSolucitudCita comandoSolucitudCita) {
        return this.manejadorAgendar.ejecutar(comandoSolucitudCita);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar", description = "Metodo utilizaco para actualizar una cita")
    public ResumenCitaDTO actualizar(@RequestBody ResumenCitaDTO resumenCitaDTO,
                                     @PathVariable Long id) {
        resumenCitaDTO.setId(id);
        return this.manejadorActualizar.ejecutar(resumenCitaDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar", description = "Metodo utilizado para eliminar una cita")
    public ComandoRespuesta<Long> eliminar(@PathVariable Long id) {
        return this.manejadorEliminar.ejecutar(id);
    }


}

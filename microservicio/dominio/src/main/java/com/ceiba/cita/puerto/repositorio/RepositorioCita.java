package com.ceiba.cita.puerto.repositorio;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.modelo.entidad.Cita;

import java.util.List;

public interface RepositorioCita {

    Long guardar(Cita cita);

    List<ResumenCitaDTO> obtenerResumenCitasPorFecha(String fecha);

    Long eliminar(Cita cita);

    void actualizar(Cita cita);
}

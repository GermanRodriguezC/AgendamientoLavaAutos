package com.ceiba.cita.servicio;


import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;


public class ServicioActualizar {

    private final RepositorioCita repositorioCita;

    public ServicioActualizar(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
    }

    public ResumenCitaDTO ejecutar(ResumenCitaDTO resumenCitaDTO) {
        var cita = Cita.reconstruir(resumenCitaDTO);
        this.repositorioCita.actualizar(cita);

        return new ResumenCitaDTO(cita.getId(), cita.getPlaca(), cita.getFecha(), cita.getHora(), cita.getValor());
    }
}

package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.SolicitudAgendar;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;

public class ServicioAgendar {

    private final RepositorioCita repositorioCita;

    public ServicioAgendar(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
    }

    public Long ejecutar(SolicitudAgendar solicitudAgendar) {
        var cita = Cita.crear(solicitudAgendar);
        return repositorioCita.guardar(cita);
    }
}

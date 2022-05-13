package com.ceiba.cita.servicio;


import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;

public class ServicioEliminar {

    private final RepositorioCita repositorioCita;

    public ServicioEliminar(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
    }

    public Long ejecutar(Long id) {
        var cita = Cita.eliminar(id);
        return repositorioCita.eliminar(cita);
    }

}

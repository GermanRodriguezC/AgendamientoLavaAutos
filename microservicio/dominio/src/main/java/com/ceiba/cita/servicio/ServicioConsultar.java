package com.ceiba.cita.servicio;


import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.ValidadorArgumento;

import java.util.List;

public class ServicioConsultar {

    private static final String REGEX_FECHA = "^([0-2][0-9]|3[0-1])(\\/|\\/)(0[1-9]|1[0-2])\\2(\\d{4})$";

    private final RepositorioCita repositorioCita;

    public ServicioConsultar(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
    }

    public List<ResumenCitaDTO> ejecutar(String fecha) {
        ValidadorArgumento.validarObligatorio(fecha, "Es necesaria la fecha deseada para realizar la consulta");
        ValidadorArgumento.validarRegex(fecha, REGEX_FECHA, "La fecha ingresada no tiene el formato dd/mm/yyyy");
        return this.repositorioCita.obtenerResumenCitasPorFecha(fecha);
    }
}

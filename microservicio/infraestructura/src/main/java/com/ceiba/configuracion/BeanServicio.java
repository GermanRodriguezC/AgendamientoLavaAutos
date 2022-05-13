package com.ceiba.configuracion;

import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioActualizar;
import com.ceiba.cita.servicio.ServicioAgendar;
import com.ceiba.cita.servicio.ServicioConsultar;
import com.ceiba.cita.servicio.ServicioEliminar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioAgendar servicioAgendar(RepositorioCita repositorioCita) {
        return new ServicioAgendar(repositorioCita);
    }

    @Bean
    public ServicioConsultar servicioConsultar(RepositorioCita repositorioCita) {
        return new ServicioConsultar(repositorioCita);
    }

    @Bean
    public ServicioActualizar servicioActualizar(RepositorioCita repositorioCita) {
        return new ServicioActualizar(repositorioCita);
    }

    @Bean
    public ServicioEliminar servicioEliminar(RepositorioCita repositorioCita) {
        return new ServicioEliminar(repositorioCita);
    }

}

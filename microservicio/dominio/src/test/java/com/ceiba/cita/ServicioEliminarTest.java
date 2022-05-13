package com.ceiba.cita;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioEliminar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServicioEliminarTest {

    @Mock
    private RepositorioCita repositorioCita;

    private ServicioEliminar servicioEliminar;

    @BeforeEach
    void setUp() {
        this.servicioEliminar = new ServicioEliminar(this.repositorioCita);
    }

    @Test
    void deberiaEliminarCita() {
        var idCita = 1L;
        Mockito.when(this.repositorioCita.eliminar(Mockito.any(Cita.class)))
                .thenReturn(idCita);

        var citaEliminada = this.servicioEliminar.ejecutar(idCita);

        Mockito.verify(this.repositorioCita, Mockito.times(1))
                .eliminar(Mockito.any(Cita.class));
        Assertions.assertNotNull(citaEliminada);
        Assertions.assertEquals(idCita, citaEliminada);
    }

}

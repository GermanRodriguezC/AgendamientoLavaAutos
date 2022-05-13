package com.ceiba.cita;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioActualizar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServicioActualizarCitaTest {

    @Mock
    private RepositorioCita repositorioCita;

    private ArgumentCaptor<Cita> actualizarArgumentCaptor;

    private ServicioActualizar servicioActualizar;

    @BeforeEach
    void setUp() {
        this.servicioActualizar = new ServicioActualizar(this.repositorioCita);
        actualizarArgumentCaptor = ArgumentCaptor.forClass(Cita.class);
    }

    @Test
    void deberiaActualizarCitaYGuardar() {
        ResumenCitaDTO resumenCitaDTO =
                new ResumenCitaDTO(1L, "CCC111", "12/12/2022", "08:00", 100000.00);
        Mockito.doNothing().when(this.repositorioCita).actualizar(Mockito.any(Cita.class));
        var cita = Cita.reconstruir(resumenCitaDTO);

        var respuestaActualizacion = this.servicioActualizar.ejecutar(resumenCitaDTO);

        Mockito.verify(this.repositorioCita, Mockito.times(1))
                .actualizar(actualizarArgumentCaptor.capture());
        Assertions.assertNotNull(respuestaActualizacion);
        Assertions.assertEquals(cita.getId(), actualizarArgumentCaptor.getValue().getId());
        Assertions.assertEquals(resumenCitaDTO.getId(), respuestaActualizacion.getId());
        Assertions.assertEquals(resumenCitaDTO.getPlaca(), respuestaActualizacion.getPlaca());
        Assertions.assertEquals(resumenCitaDTO.getFecha(), respuestaActualizacion.getFecha());
        Assertions.assertEquals(resumenCitaDTO.getHora(), respuestaActualizacion.getHora());
        Assertions.assertEquals(resumenCitaDTO.getValor(), respuestaActualizacion.getValor());
    }
}

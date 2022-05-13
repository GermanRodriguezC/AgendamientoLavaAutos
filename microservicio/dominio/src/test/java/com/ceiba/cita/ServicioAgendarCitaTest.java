package com.ceiba.cita;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.SolicitudAgendar;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioAgendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServicioAgendarCitaTest {

    @Mock
    private RepositorioCita repositorioCita;

    private ArgumentCaptor<Cita> agendarArgumentCaptor;

    private ServicioAgendar servicioAgendar;

    @BeforeEach
    void setUp() {
        this.servicioAgendar = new ServicioAgendar(this.repositorioCita);
        agendarArgumentCaptor = ArgumentCaptor.forClass(Cita.class);
    }

    @Test
    void deberiaCrearCitaYGuardar() {
        Mockito.when(repositorioCita.guardar(Mockito.any(Cita.class)))
                .thenReturn(1L);
        var solicitudAgendar = new SolicitudAgendar("CCC111", "11/12/2022", "08:00");
        var cita = Cita.crear(solicitudAgendar);

        var idCita = this.servicioAgendar.ejecutar(solicitudAgendar);

        Mockito.verify(this.repositorioCita, Mockito.times(1))
                .guardar(agendarArgumentCaptor.capture());
        Assertions.assertNotNull(idCita);
        Assertions.assertEquals(1L, idCita);
        Assertions.assertEquals(cita.getPlaca(), agendarArgumentCaptor.getValue().getPlaca());
        Assertions.assertEquals(cita.getFecha(), agendarArgumentCaptor.getValue().getFecha());
        Assertions.assertEquals(cita.getHora(), agendarArgumentCaptor.getValue().getHora());
    }
}

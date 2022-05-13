package com.ceiba.cita;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioConsultar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ServicioConsultarCitaTest {

    @Mock
    private RepositorioCita repositorioCita;

    private ServicioConsultar servicioConsultar;

    @BeforeEach
    void setUp() {
        this.servicioConsultar = new ServicioConsultar(this.repositorioCita);
    }

    @Test
    void deberiaConsultarCitas() {
        List<ResumenCitaDTO> citas = new ArrayList<>();
        citas.add(new ResumenCitaDTO(1L, "CCC111", "11/12/2022", "08:00", 100000.00));
        citas.add(new ResumenCitaDTO(2L, "CCC222", "11/12/2022", "09:00", 100000.00));
        Mockito.when(this.repositorioCita.obtenerResumenCitasPorFecha(Mockito.anyString()))
                .thenReturn(citas);

        var respuestaCitas = this.servicioConsultar.ejecutar("11/12/2021");

        Mockito.verify(this.repositorioCita, Mockito.times(1))
                .obtenerResumenCitasPorFecha(Mockito.anyString());
        Assertions.assertNotNull(respuestaCitas);
        Assertions.assertEquals(citas.size(), respuestaCitas.size());
        Assertions.assertEquals(citas.get(0), respuestaCitas.get(0));
        Assertions.assertEquals(citas.get(1), respuestaCitas.get(1));
    }
}

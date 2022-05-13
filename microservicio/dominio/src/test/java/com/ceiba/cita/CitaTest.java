package com.ceiba.cita;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.SolicitudAgendar;
import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CitaTest {

    private static final Long ID = 1L;
    private static final String PLACA = "AAA111";
    private static final String FECHA = "13/12/2022";
    private static final String HORA = "09:00";
    private static final Double VALOR = 100000.00;

    @Test
    void deberiaCrearLaCitaCorrectamenteEnDiaLaboral() {
        var cita = Cita.crear(new SolicitudAgendar(PLACA, FECHA, HORA));

        Assertions.assertNotNull(cita);
        Assertions.assertEquals(PLACA, cita.getPlaca());
        Assertions.assertEquals(FECHA, cita.getFecha());
        Assertions.assertEquals(HORA, cita.getHora());
        Assertions.assertEquals(100000.00, cita.getValor());
    }

    @Test
    void deberiaCrearLaCitaCorrectamenteElSabado() {
        var cita = Cita.crear(new SolicitudAgendar(PLACA, "10/12/2022", HORA));

        Assertions.assertNotNull(cita);
        Assertions.assertEquals(PLACA, cita.getPlaca());
        Assertions.assertEquals("12/12/2022", cita.getFecha());
        Assertions.assertEquals(HORA, cita.getHora());
        Assertions.assertEquals(VALOR, cita.getValor());
    }

    @Test
    void deberiaCrearLaCitaCorrectamenteElDomingo() {
        var cita = Cita.crear(new SolicitudAgendar(PLACA, "10/12/2022", HORA));

        Assertions.assertNotNull(cita);
        Assertions.assertEquals(PLACA, cita.getPlaca());
        Assertions.assertEquals("12/12/2022", cita.getFecha());
        Assertions.assertEquals(HORA, cita.getHora());
        Assertions.assertEquals(VALOR, cita.getValor());
    }

    @Test
    void deberiaCrearLaCitaCorrectamenteEnDiaFestivo() {
        var cita = Cita.crear(new SolicitudAgendar(PLACA, "08/12/2022", HORA));

        Assertions.assertNotNull(cita);
        Assertions.assertEquals(PLACA, cita.getPlaca());
        Assertions.assertEquals("08/12/2022", cita.getFecha());
        Assertions.assertEquals(HORA, cita.getHora());
        Assertions.assertEquals(200000.00, cita.getValor());
    }


    @Test
    void deberiaReconstruirLaCitaCorrectamenteEnDiaLaboral() {
        var cita = Cita.reconstruir(new ResumenCitaDTO(ID, PLACA, FECHA, HORA, 0.00));

        Assertions.assertNotNull(cita);
        Assertions.assertEquals(ID, cita.getId());
        Assertions.assertEquals(PLACA, cita.getPlaca());
        Assertions.assertEquals(FECHA, cita.getFecha());
        Assertions.assertEquals(HORA, cita.getHora());
        Assertions.assertEquals(VALOR, cita.getValor());
    }

    @Test
    void deberiaReconstruirLaCitaCorrectamenteElSabado() {
        var cita = Cita.reconstruir(new ResumenCitaDTO(ID, PLACA, "10/12/2022", HORA, 0.00));

        Assertions.assertNotNull(cita);
        Assertions.assertEquals(ID, cita.getId());
        Assertions.assertEquals(PLACA, cita.getPlaca());
        Assertions.assertEquals("12/12/2022", cita.getFecha());
        Assertions.assertEquals(HORA, cita.getHora());
        Assertions.assertEquals(VALOR, cita.getValor());
    }

    @Test
    void deberiaReconstruirLaCitaCorrectamenteElDomingo() {
        var cita = Cita.reconstruir(new ResumenCitaDTO(ID, PLACA, "10/12/2022", HORA, 0.00));

        Assertions.assertNotNull(cita);
        Assertions.assertEquals(ID, cita.getId());
        Assertions.assertEquals(PLACA, cita.getPlaca());
        Assertions.assertEquals("12/12/2022", cita.getFecha());
        Assertions.assertEquals(HORA, cita.getHora());
        Assertions.assertEquals(VALOR, cita.getValor());
    }

    @Test
    void deberiaReconstruirLaCitaCorrectamenteEnDiaFestivo() {
        var cita = Cita.reconstruir(new ResumenCitaDTO(ID, PLACA, "08/12/2022", HORA, 0.00));

        Assertions.assertNotNull(cita);
        Assertions.assertEquals(ID, cita.getId());
        Assertions.assertEquals(PLACA, cita.getPlaca());
        Assertions.assertEquals("08/12/2022", cita.getFecha());
        Assertions.assertEquals(HORA, cita.getHora());
        Assertions.assertEquals(200000.00, cita.getValor());
    }

    @Test
    void deberiaEliminarLaCitaCorrectamente() {
        var cita = Cita.eliminar(ID);

        Assertions.assertNotNull(cita);
        Assertions.assertEquals(ID, cita.getId());
    }

    @Test
    void citaConFormatoDeFechaInvalidoDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> Cita.reconstruir(new ResumenCitaDTO(ID, PLACA, "08-12-2022", HORA, 0.00)),
                ExcepcionValorInvalido.class,
                "La fecha ingresada no tiene el formato dd/mm/yyyy");
    }

    @Test
    void citaSinPlacaDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> Cita.crear(new SolicitudAgendar(null, "08/12/2022", HORA)),
                ExcepcionValorObligatorio.class,
                "Es necesaria la placa para generar la cita");
    }

}

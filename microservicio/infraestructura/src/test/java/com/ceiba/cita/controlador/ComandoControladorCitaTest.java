package com.ceiba.cita.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorCita.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorCitaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    void creaCitaExitosa() throws Exception {
        var comandoCitaTestDataBuilder = new ComandoSolicitudCitaTestDataBuilder().build();

        var resultado = mocMvc.perform(post("/cita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCitaTestDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultado.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, RespuestaAgendar.class);

        Assertions.assertNotNull(respuesta);
        Assertions.assertTrue(respuesta.getValor() > 0);
    }

    @Test
    void eliminaCitaExitosa() throws Exception {
        mocMvc.perform(delete("/cita/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor").exists());
    }

    @Test
    void actualizarCitaExitosa() throws Exception {
        var resumenCitaDTOTestDataBuilder = new ResumenCitaDTOTestDataBuilder().build();

        var resultado = mocMvc.perform(put("/cita/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resumenCitaDTOTestDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultado.getResponse().getContentAsString();

        var respuesta = objectMapper.readValue(jsonResult, ResumenCitaDTO.class);

        Assertions.assertNotNull(respuesta);
        Assertions.assertEquals(resumenCitaDTOTestDataBuilder.getId(), respuesta.getId());
        Assertions.assertEquals(resumenCitaDTOTestDataBuilder.getPlaca(), respuesta.getPlaca());
        Assertions.assertEquals(resumenCitaDTOTestDataBuilder.getFecha(), respuesta.getFecha());
        Assertions.assertEquals(resumenCitaDTOTestDataBuilder.getHora(), respuesta.getHora());
        Assertions.assertEquals(resumenCitaDTOTestDataBuilder.getValor(), respuesta.getValor());
    }


}

package com.ceiba.cita.controlador;

import com.ceiba.ApplicationMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorCita.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorCitaTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    void consultarFacturasAnuladas() throws Exception {

        mocMvc.perform(get("/cita")
                        .param("fecha", "12/09/2022"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id", is(200)))
                .andExpect(jsonPath("$[0].placa", is("CBA321")))
                .andExpect(jsonPath("$[0].fecha", is("12/09/2022")))
                .andExpect(jsonPath("$[0].hora", is("11:30")))
                .andExpect(jsonPath("$[0].valor", is(100000.00)));
    }

}

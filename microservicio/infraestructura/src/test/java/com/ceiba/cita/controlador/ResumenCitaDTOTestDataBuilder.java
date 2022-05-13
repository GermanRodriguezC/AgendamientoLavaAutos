package com.ceiba.cita.controlador;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;

public class ResumenCitaDTOTestDataBuilder {
    public ResumenCitaDTO build() {
        return new ResumenCitaDTO(100L, "ABC123", "30/03/2022", "15:30", 100000.00);
    }
}

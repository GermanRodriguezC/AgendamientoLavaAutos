package com.ceiba.cita.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResumenCitaDTO {

    private Long id;

    private String placa;

    private String fecha;

    private String hora;

    private double valor;

}

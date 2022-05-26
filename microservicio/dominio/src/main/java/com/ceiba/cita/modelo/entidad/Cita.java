package com.ceiba.cita.modelo.entidad;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.dominio.ValidadorArgumento;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class Cita {

    private static final String REGEX_FECHA = "^([0-2][0-9]|3[0-1])(\\/|\\/)(0[1-9]|1[0-2])\\2(\\d{4})$";
    private static final String REGEX_HORA = "^([0-1][0-9]|2[0-3])(:)([0-5][0-9])$";
    private static final String REGEX_PLACA = "^[A-Z0-9]{5,6}$";

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private Long id;
    private String placa;
    private String fecha;
    private String hora;
    private double valor;

    private Cita(String placa, String fecha, String hora, double valor) {
        this.placa = placa;
        this.fecha = fecha;
        this.hora = hora;
        this.valor = valor;
    }

    private Cita(Long id, String placa, String fecha, String hora, double valor) {
        this.id = id;
        this.placa = placa;
        this.fecha = fecha;
        this.hora = hora;
        this.valor = valor;
    }

    private Cita(Long id) {
        this.id = id;
    }

    public static Cita crear(SolicitudAgendar solicitudAgendar) {
        ValidadorArgumento.validarFinDeSemana(LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT)), "No se pueden generar citas un fin de semana");
        ValidadorArgumento.validarObligatorio(solicitudAgendar.getPlaca(), "Es necesaria la placa para generar la cita");
        ValidadorArgumento.validarRegex(solicitudAgendar.getPlaca(), REGEX_PLACA, "No es una placa válida");
        ValidadorArgumento.validarObligatorio(solicitudAgendar.getFecha(), "Es necesaria la fecha deseada para generar la cita");
        ValidadorArgumento.validarRegex(solicitudAgendar.getFecha(), REGEX_FECHA, "La fecha ingresada no tiene el formato dd/mm/yyyy");
        ValidadorArgumento.validarObligatorio(solicitudAgendar.getHora(), "Es necesaria la hora para generar la cita");
        ValidadorArgumento.validarRegex(solicitudAgendar.getHora(), REGEX_HORA, "La hora ingresada no tiene el formato hh:mm");
        return new Cita(solicitudAgendar.getPlaca(), fechaReal(solicitudAgendar.getFecha()), solicitudAgendar.getHora(), getValorAPagar(fechaReal(solicitudAgendar.getFecha())));
    }

    public static Cita reconstruir(ResumenCitaDTO resumenCitaDTO) {
        ValidadorArgumento.validarObligatorio(resumenCitaDTO.getId(), "Id del cliente es requerido");
        ValidadorArgumento.validarObligatorio(resumenCitaDTO.getPlaca(), "Es necesaria la placa para generar la cita");
        ValidadorArgumento.validarRegex(resumenCitaDTO.getPlaca(), REGEX_PLACA, "No es una placa válida");
        ValidadorArgumento.validarObligatorio(resumenCitaDTO.getFecha(), "Es necesaria la fecha deseada para generar la cita");
        ValidadorArgumento.validarRegex(resumenCitaDTO.getFecha(), REGEX_FECHA, "La fecha ingresada no tiene el formato dd/mm/yyyy");
        ValidadorArgumento.validarObligatorio(resumenCitaDTO.getHora(), "Es necesaria la hora para generar la cita");
        ValidadorArgumento.validarRegex(resumenCitaDTO.getHora(), REGEX_HORA, "La hora ingresada no tiene el formato hh:mm");
        return new Cita(resumenCitaDTO.getId(), resumenCitaDTO.getPlaca(), fechaReal(resumenCitaDTO.getFecha()), resumenCitaDTO.getHora(), getValorAPagar(fechaReal(resumenCitaDTO.getFecha())));
    }

    public static Cita eliminar(Long id) {
        ValidadorArgumento.validarObligatorio(id, "Id del cliente es requerido");
        return new Cita(id);
    }

    private static String fechaReal(String fechaIngresada) {
        LocalDate fecha = LocalDate.parse(fechaIngresada, DateTimeFormatter.ofPattern(DATE_FORMAT));
        if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY)
            fecha = fecha.plusDays(2);
        else if (fecha.getDayOfWeek() == DayOfWeek.SUNDAY)
            fecha = fecha.plusDays(1);
        return fecha.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    private static Double getValorAPagar(String fechaCita) {
        if (Feriado.getFeriados().contains(fechaCita))
            return 200000.00;
        else
            return 100000.00;
    }

    public double getValor() {
        return this.valor;
    }

    public String getPlaca() {
        return this.placa;
    }

    public String getFecha() {
        return this.fecha;
    }

    public String getHora() {
        return this.hora;
    }

    public Long getId() {
        return id;
    }

}

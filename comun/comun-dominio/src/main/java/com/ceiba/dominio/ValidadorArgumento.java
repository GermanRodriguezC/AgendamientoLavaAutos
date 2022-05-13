package com.ceiba.dominio;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorArgumento {

    private ValidadorArgumento() {
    }

    public static void validarObligatorio(Object valor, String mensaje) {
        if (valor == null) {
            throw new ExcepcionValorObligatorio(mensaje);
        }
    }

    public static void validarRegex(String dato, String regex, String mensaje) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dato);

        if (!matcher.matches()) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

    public static void validarFinDeSemana(String valor, String mensaje) {
        if (LocalDate.parse(valor, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getDayOfWeek() == DayOfWeek.SATURDAY ||
                LocalDate.parse(valor, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }
}

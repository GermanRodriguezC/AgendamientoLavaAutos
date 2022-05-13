package com.ceiba.cita.adaptador.repositorio;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoCitaResumen implements RowMapper<ResumenCitaDTO> {

    @Override
    public ResumenCitaDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var placa = resultSet.getString("placa");
        var fecha = resultSet.getString("fecha");
        var hora = resultSet.getString("hora");
        var valor = resultSet.getDouble("valor");
        return new ResumenCitaDTO(id, placa, fecha, hora, valor);
    }

}

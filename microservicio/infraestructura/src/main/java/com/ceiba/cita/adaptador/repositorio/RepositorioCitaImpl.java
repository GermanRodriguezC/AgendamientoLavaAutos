package com.ceiba.cita.adaptador.repositorio;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioCitaImpl implements RepositorioCita {

    public static final String FECHA = "fecha";
    @SqlStatement(namespace = "cita", value = "crear.sql")
    private static String sqlCrear;
    @SqlStatement(namespace = "cita", value = "obtenercitasporfecha.sql")
    private static String sqlObtenerCitasPorFecha;
    @SqlStatement(namespace = "cita", value = "actualizar.sql")
    private static String sqlActualizar;
    @SqlStatement(namespace = "cita", value = "eliiminarcita.sql")

    private static String sqlEliminar;
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoCitaResumen mapeoCitaResumen;

    public RepositorioCitaImpl(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoCitaResumen mapeoCitaResumen) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoCitaResumen = mapeoCitaResumen;
    }

    @Override
    public Long guardar(Cita cita) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("placa", cita.getPlaca());
        paramSource.addValue(FECHA, cita.getFecha());
        paramSource.addValue("hora", cita.getHora());
        paramSource.addValue("valor", cita.getValor());
        return this.customNamedParameterJdbcTemplate.upsert(paramSource, sqlCrear);
    }

    @Override
    public List<ResumenCitaDTO> obtenerResumenCitasPorFecha(String fecha) {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerCitasPorFecha, new MapSqlParameterSource()
                        .addValue(FECHA, fecha), mapeoCitaResumen);
    }

    @Override
    public void actualizar(Cita cita) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", cita.getId());
        paramSource.addValue("placa", cita.getPlaca());
        paramSource.addValue(FECHA, cita.getFecha());
        paramSource.addValue("hora", cita.getHora());
        paramSource.addValue("valor", cita.getValor());
        this.customNamedParameterJdbcTemplate.upsert(paramSource, sqlActualizar);
    }

    @Override
    public Long eliminar(Cita cita) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", cita.getId());
        this.customNamedParameterJdbcTemplate.delete(paramSource, sqlEliminar);
        return cita.getId();
    }
}

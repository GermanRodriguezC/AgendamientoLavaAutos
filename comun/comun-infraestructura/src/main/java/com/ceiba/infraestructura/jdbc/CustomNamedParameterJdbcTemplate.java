package com.ceiba.infraestructura.jdbc;

import com.ceiba.dominio.ValidadorArgumento;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class CustomNamedParameterJdbcTemplate {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CustomNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Long create(MapSqlParameterSource sqlParameterSource, String sql) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder, new String[]{"id"});
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void update(MapSqlParameterSource sqlParameterSource, String sql) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder, new String[]{"id"});
        ValidadorArgumento.validarObligatorio(keyHolder.getKey(), "No se encontró un registro de la cita");
        Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void delete(MapSqlParameterSource mapSqlParameterSource, String sql) {
        this.namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return this.namedParameterJdbcTemplate;
    }
}

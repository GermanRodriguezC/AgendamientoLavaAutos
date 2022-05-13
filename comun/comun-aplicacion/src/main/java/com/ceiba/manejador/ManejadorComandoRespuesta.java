package com.ceiba.manejador;

import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

public interface ManejadorComandoRespuesta<C, R> {

    @Transactional
    R ejecutar(C comando) throws IOException;
}
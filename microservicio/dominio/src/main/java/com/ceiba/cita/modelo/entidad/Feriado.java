package com.ceiba.cita.modelo.entidad;

import java.util.Set;

public final class Feriado {

    private Feriado() {
    }

    public static Set<String> getFeriados() {
        return Set.of(
                "01/01/2022",
                "10/01/2022",
                "21/03/2022",
                "14/04/2022",
                "15/04/2022",
                "01/05/2022",
                "30/05/2022",
                "20/06/2022",
                "27/06/2022",
                "04/07/2022",
                "20/07/2022",
                "07/08/2022",
                "15/08/2022",
                "17/10/2022",
                "07/11/2022",
                "14/11/2022",
                "08/12/2022",
                "25/12/2022"
        );
    }

}

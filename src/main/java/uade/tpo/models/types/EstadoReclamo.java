package uade.tpo.models.types;

import java.util.Arrays;
import java.util.Optional;

/**
 * 
 */
public enum EstadoReclamo {
    PENDIENTE("PENDIENTE"),
    DESESTIMADO("DESESTIMADO"),
    ANULADO("ANULADO"),
    NUEVO("NUEVO"),
    ABIERTO("ABIERTO"),
    EN_PROCESO("EN_PROCESO"),
    TERMINADO("TERMINADO");

    private String name;

    EstadoReclamo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<EstadoReclamo> get(String name) {
        return Arrays.stream(EstadoReclamo.values())
                .filter(env -> env.name.equals(name.replace(" ", "_")))
                .findFirst();
    }
}
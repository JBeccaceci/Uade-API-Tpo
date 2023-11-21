package uade.tpo.models.types;

import java.util.Arrays;
import java.util.Optional;

/**
 * 
 */
public enum TipoReclamo {
    ROTURA("ROTURA"),
    PROBLEMA("PROBLEMA"),
    MEJORA("MEJORA");

    private String name;

    TipoReclamo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<TipoReclamo> get(String name) {
        return Arrays.stream(TipoReclamo.values())
                .filter(env -> env.name.equals(name))
                .findFirst();
    }
}
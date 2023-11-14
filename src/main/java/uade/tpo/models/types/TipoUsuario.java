package uade.tpo.models.types;

import java.util.Arrays;
import java.util.Optional;

public enum TipoUsuario {
    PROPIETARIO("PROPIETARIO"),
    INQUILINO("INQUILINO");

    private String name;

    TipoUsuario(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<TipoUsuario> get(String name) {
        return Arrays.stream(TipoUsuario.values())
                .filter(env -> env.name.equals(name))
                .findFirst();
    }
}

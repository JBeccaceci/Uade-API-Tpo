package uade.tpo.models.types;

import java.util.Arrays;
import java.util.Optional;

public enum TipoRole {
    ADMIN("ADMIN"),
    INQUILINO("INQUILINO");

    private String name;

    TipoRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<TipoRole> get(String name) {
        return Arrays.stream(TipoRole.values())
                .filter(env -> env.name.equals(name.replace(" ", "_")))
                .findFirst();
    }
}

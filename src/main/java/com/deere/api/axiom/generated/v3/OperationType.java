package com.deere.api.axiom.generated.v3;

import java.util.Optional;

public enum OperationType {
    SEED("S"),
    APPLICATION("A"),
    HARVEST("H"),
    TILLAGE("T"),
    UNKNOWN("U"),
    BALING("B"),
    CONDITIONING("C"),
    MISCELLANEOUS("M"),
    MOWING("W"),
    SCOUTING("O"),
    SOIL_SAMPLING("P"),
    TRANSPORT("R");

    private String discriminator;

    OperationType(String discriminator) {
        this.discriminator = discriminator;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public static Optional<OperationType> fromDiscriminator(String discriminator) {
        for (OperationType operationType : values()) {
            if (operationType.getDiscriminator().equalsIgnoreCase(discriminator)) {
                return Optional.of(operationType);
            }
        }
        return Optional.empty();
    }
}

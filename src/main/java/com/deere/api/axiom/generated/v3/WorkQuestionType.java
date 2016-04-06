package com.deere.api.axiom.generated.v3;

import java.util.Optional;

public enum WorkQuestionType {
    BOOLEAN_QUESTION("B"),
    MULTIPLE_CHOICE_QUESTION("M"),
    NUMERIC_QUESTION("N"),
    FREE_TEXT_QUESTION("F");

    private String discriminator;

    WorkQuestionType(String discriminator) {
        this.discriminator = discriminator;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public static Optional<WorkQuestionType> fromDiscriminator(String discriminator) {
        for (WorkQuestionType workQuestionType : values()) {
            if (workQuestionType.getDiscriminator().equalsIgnoreCase(discriminator)) {
                return Optional.of(workQuestionType);
            }
        }
        return Optional.empty();
    }
}

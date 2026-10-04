package dev.railroadide.railroad.settings;

import lombok.Getter;

/**
 * Defines how the caret's one-based line and column are displayed
 * in the IDE status bar.
 */
public enum CaretPositionFormat {
    /**
     * Displays the line and column separated by a colon, for example {@code 1:1}.
     */
    COLON("railroad.ide.status_bar.caret_position_format.colon"),

    /**
     * Displays labeled line and column numbers, for example {@code Ln 1, Col 1}.
     */
    TEXT("railroad.ide.status_bar.caret_position_format.text");

    @Getter
    private final String translationKey;

    CaretPositionFormat(String translationKey) {
        this.translationKey = translationKey;
    }
}

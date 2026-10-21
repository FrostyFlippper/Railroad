package dev.railroadide.railroad.ide.ui;

import dev.railroadide.railroad.ide.ui.codeeditor.TextEditorPane;
import javafx.scene.layout.StackPane;
import lombok.Getter;

import java.nio.file.Path;

/**
 * Wraps a text editor in a container that supports placing controls above it,
 * such as a Find and Replace bar.
 *
 * <p>
 * The inner editor handles text editing and file operations. This host
 * provides the layout and exposes access to the editor and its file path.
 */
public class EditorHostPane extends StackPane {
    @Getter
    private final TextEditorPane textEditorPane;

    /**
     * creates a new EditorHostPane that contains a TextEditorPane
     *
     * @param textEditorPane the editor to display inside the host
     */
    public EditorHostPane(TextEditorPane textEditorPane) {
        this.textEditorPane = textEditorPane;
        this.getChildren().add(textEditorPane);
    }

    /**
     * returns the file path of the text editor pane field
     *
     * @return the file path of the text editor pane field
     */
    public Path getTextEditorFilePath() {
        return this.textEditorPane.getFilePath();
    }
}

package dev.railroadide.railroad.ide.ui;

import dev.railroadide.railroad.ide.ui.codeeditor.TextEditorPane;
import javafx.scene.layout.BorderPane;
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
public class EditorHostPane extends BorderPane {
    @Getter
    private final TextEditorPane textEditorPane;
    private final EditorFindBar editorFindBar;

    /**
     * creates a new EditorHostPane that contains a TextEditorPane
     *
     * @param textEditorPane the editor to display inside the host
     */
    public EditorHostPane(TextEditorPane textEditorPane) {
        this.editorFindBar = new EditorFindBar();
        this.textEditorPane = textEditorPane;

        this.setTop(editorFindBar);
        this.setCenter(textEditorPane);

        this.editorFindBar.getCloseButton().setOnAction(_ -> closeFindBar());

        //closeFindBar();
    }

    /**
     * returns the file path of the text editor pane field
     *
     * @return the file path of the text editor pane field
     */
    public Path getTextEditorFilePath() {
        return this.textEditorPane.getFilePath();
    }

    public void openFindBar(){
        this.editorFindBar.setVisible(true);
        this.editorFindBar.setManaged(true);
        this.editorFindBar.getSearchField().requestFocus();
    }

    public void closeFindBar(){
        this.editorFindBar.setVisible(false);
        this.editorFindBar.setManaged(false);
    }
}

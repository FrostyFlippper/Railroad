package dev.railroadide.railroad.ide.language.impl;

import dev.railroadide.railroad.ide.language.BaseLanguageSupport;
import dev.railroadide.railroad.ide.language.EditorOpenView;
import dev.railroadide.railroad.ide.syntaxhighlighting.JsonSyntaxHighlighting;
import dev.railroadide.railroad.ide.ui.EditorHostPane;
import dev.railroadide.railroad.ide.ui.JsonCodeEditorPane;
import dev.railroadide.railroad.ide.ui.codeeditor.CodeEditorConfig;
import dev.railroadide.railroad.plugin.spi.dto.Project;

import java.nio.file.Path;
import java.util.Set;

/**
 * Provides JSON text editing with JSON syntax highlighting.
 */
public final class JsonLanguageSupport extends BaseLanguageSupport {
    /**
     * Creates language support for JSON source files.
     */
    public JsonLanguageSupport() {
        super("json", "JSON", Set.of("json"));
    }

    @Override
    public EditorOpenView open(Project project, Path file) {
        var editorPane = new JsonCodeEditorPane(
            project,
            file,
            CodeEditorConfig.fromLanguageSupport(project, file, this, JsonSyntaxHighlighting::computeHighlighting));
        return new EditorOpenView(new EditorHostPane(editorPane), editorPane, languageId());
    }
}

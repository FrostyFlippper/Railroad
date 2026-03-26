package dev.railroadide.railroad.plugin.spi.inspection;

import dev.railroadide.railroad.ide.sst.semantic.api.SemanticModel;
import dev.railroadide.railroad.ide.sst.syntax.api.SyntaxTree;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Read-only input provided to plugin inspections.
 */
public record JavaInspectionContext(
        Path filePath,
        String documentText,
        SemanticModel semanticModel
) {
    public JavaInspectionContext {
        filePath = Objects.requireNonNull(filePath, "filePath");
        documentText = Objects.requireNonNull(documentText, "documentText");
        semanticModel = Objects.requireNonNull(semanticModel, "semanticModel");
    }

    public SyntaxTree syntaxTree() {
        return semanticModel.syntaxTree();
    }
}

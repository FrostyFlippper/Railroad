package dev.railroadide.railroad.plugin.spi.inspection;

import dev.railroadide.railroad.ide.sst.semantic.api.SemanticModel;
import dev.railroadide.railroad.ide.sst.syntax.api.SyntaxTree;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Read-only input provided to plugin inspections.
 * <p>
 * This is the legacy inspection entry context used by {@link JavaInspection}. New rules
 * typically receive {@link JavaRuleContext}, which wraps this data and adds many helper
 * methods for inspection authors.
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

    /**
     * Convenience accessor for {@code semanticModel().syntaxTree()}.
     *
     * @return the syntax tree for the inspected file
     */
    public SyntaxTree syntaxTree() {
        return semanticModel.syntaxTree();
    }
}

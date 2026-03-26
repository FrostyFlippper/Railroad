package dev.railroadide.railroad.plugin.spi.inspection;

import dev.railroadide.railroad.ide.sst.semantic.api.SemanticDiagnostic;
import dev.railroadide.railroad.ide.sst.syntax.api.SyntaxNode;

/**
 * Sink used by inspections to report diagnostics.
 */
public interface JavaInspectionReporter {
    void report(SemanticDiagnostic diagnostic);

    default void error(String code, String message, SyntaxNode node) {
        report(new SemanticDiagnostic(
                SemanticDiagnostic.Severity.ERROR,
                code,
                message,
                node.start(),
                node.end(),
                node
        ));
    }

    default void warning(String code, String message, SyntaxNode node) {
        report(new SemanticDiagnostic(
                SemanticDiagnostic.Severity.WARNING,
                code,
                message,
                node.start(),
                node.end(),
                node
        ));
    }

    default void info(String code, String message, SyntaxNode node) {
        report(new SemanticDiagnostic(
                SemanticDiagnostic.Severity.INFO,
                code,
                message,
                node.start(),
                node.end(),
                node
        ));
    }
}

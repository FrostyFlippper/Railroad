package dev.railroadide.railroad.plugin.spi.inspection;

import dev.railroadide.railroad.ide.sst.semantic.api.SemanticDiagnostic;

import java.util.Set;

/**
 * A single Java inspection rule with metadata and evaluation logic.
 */
public interface JavaInspectionRule {
    /**
     * Stable namespaced id for this rule (e.g. {@code my.plugin:my-rule}).
     */
    String id();

    /**
     * Default diagnostic severity for reports produced by this rule.
     */
    SemanticDiagnostic.Severity defaultSeverity();

    /**
     * Message template used by {@link JavaInspectionRuleReporter#report}.
     */
    String messageTemplate();

    /**
     * Optional tags/categories (e.g. {@code imports}, {@code types}).
     */
    default Set<String> tags() {
        return Set.of();
    }

    /**
     * Evaluates the rule and emits diagnostics through the provided reporter.
     */
    void evaluate(JavaRuleContext context, JavaInspectionRuleReporter reporter);
}

package dev.railroadide.railroad.plugin.spi.inspection;

import dev.railroadide.railroad.ide.sst.syntax.api.SyntaxNode;

/**
 * Reporter used by a single Java inspection rule.
 * <p>
 * Reported diagnostics automatically inherit rule-level metadata such as the rule id,
 * severity, and message template. In most cases a rule only needs to supply the offending
 * node and any template arguments.
 */
public interface JavaInspectionRuleReporter {
    /**
     * Reports a diagnostic for the supplied node using the rule's message template.
     *
     * @param node syntax node to highlight
     * @param messageArgs arguments interpolated into the rule message template
     * @throws NullPointerException if {@code node} is {@code null}
     */
    void report(SyntaxNode node, Object... messageArgs);

    /**
     * Reports a diagnostic for the supplied node using an explicit message instead of the
     * rule template.
     *
     * @param node syntax node to highlight
     * @param message explicit diagnostic message
     * @throws NullPointerException if any argument is {@code null}
     */
    void reportMessage(SyntaxNode node, String message);
}

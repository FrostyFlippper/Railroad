package dev.railroadide.railroad.plugin.spi.inspection;

import dev.railroadide.railroad.ide.sst.syntax.api.SyntaxNode;

/**
 * Reporter used by a single Java inspection rule.
 */
public interface JavaInspectionRuleReporter {
    /**
     * Report using the rule's message template and provided args.
     */
    void report(SyntaxNode node, Object... messageArgs);

    /**
     * Report using an explicit message.
     */
    void reportMessage(SyntaxNode node, String message);
}

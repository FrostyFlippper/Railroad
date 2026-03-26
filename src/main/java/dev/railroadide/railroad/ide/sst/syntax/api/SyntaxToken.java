package dev.railroadide.railroad.ide.sst.syntax.api;

import java.util.List;

/**
 * Public token API exposed to consumers.
 */
public interface SyntaxToken extends SyntaxNode {
    String text();

    @Override
    default List<SyntaxNode> children() {
        return List.of();
    }
}

package dev.railroadide.railroad.ide.sst.syntax.api;

import java.util.List;
import java.util.Optional;

/**
 * Public syntax node API exposed to consumers.
 *
 * Contract:
 * - offsets are zero-based, half-open ranges [start, end)
 * - node width is {@code end - start}
 * - token nodes have no children
 * - parent/child links are consistent
 * - child ranges are contiguous and fully cover parent range
 */
public interface SyntaxNode {
    SyntaxKind kind();

    int start();

    int end();

    List<SyntaxNode> children();

    Optional<SyntaxNode> parent();

    default int width() {
        return Math.max(0, end() - start());
    }
}

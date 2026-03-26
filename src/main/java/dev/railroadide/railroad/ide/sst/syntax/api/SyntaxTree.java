package dev.railroadide.railroad.ide.sst.syntax.api;

import java.util.Objects;

/**
 * Public syntax tree entry point.
 */
public final class SyntaxTree {
    private final SyntaxNode root;

    public SyntaxTree(SyntaxNode root) {
        this.root = Objects.requireNonNull(root, "root");
    }

    public SyntaxNode root() {
        return root;
    }
}

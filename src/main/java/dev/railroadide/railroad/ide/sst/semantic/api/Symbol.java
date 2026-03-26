package dev.railroadide.railroad.ide.sst.semantic.api;

import dev.railroadide.railroad.ide.sst.syntax.api.SyntaxNode;

import java.util.Optional;

/**
 * Public semantic symbol contract.
 */
public interface Symbol {
    SymbolKind kind();

    String simpleName();

    Optional<String> qualifiedName();

    Optional<SyntaxNode> declaration();
}

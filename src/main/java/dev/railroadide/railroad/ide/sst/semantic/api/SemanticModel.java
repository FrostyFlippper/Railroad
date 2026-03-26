package dev.railroadide.railroad.ide.sst.semantic.api;

import dev.railroadide.railroad.ide.sst.syntax.api.SyntaxNode;
import dev.railroadide.railroad.ide.sst.syntax.api.SyntaxTree;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable semantic analysis output for a syntax tree.
 */
public final class SemanticModel {
    private final SyntaxTree syntaxTree;
    private final Scope rootScope;
    private final Map<SyntaxNode, Symbol> declaredSymbols;
    private final Map<SyntaxNode, Symbol> resolvedSymbols;
    private final Map<SyntaxNode, Type> inferredTypes;
    private final List<SemanticDiagnostic> diagnostics;

    private SemanticModel(
            SyntaxTree syntaxTree,
            Scope rootScope,
            Map<SyntaxNode, Symbol> declaredSymbols,
            Map<SyntaxNode, Symbol> resolvedSymbols,
            Map<SyntaxNode, Type> inferredTypes,
            List<SemanticDiagnostic> diagnostics
    ) {
        this.syntaxTree = Objects.requireNonNull(syntaxTree, "syntaxTree");
        this.rootScope = Objects.requireNonNull(rootScope, "rootScope");
        this.declaredSymbols = copyIdentityMap(declaredSymbols, "declaredSymbols");
        this.resolvedSymbols = copyIdentityMap(resolvedSymbols, "resolvedSymbols");
        this.inferredTypes = copyIdentityMap(inferredTypes, "inferredTypes");
        this.diagnostics = List.copyOf(Objects.requireNonNull(diagnostics, "diagnostics"));
    }

    public SyntaxTree syntaxTree() {
        return syntaxTree;
    }

    public Scope rootScope() {
        return rootScope;
    }

    public Optional<Symbol> declaredSymbol(SyntaxNode node) {
        return Optional.ofNullable(declaredSymbols.get(Objects.requireNonNull(node, "node")));
    }

    public Optional<Symbol> resolvedSymbol(SyntaxNode node) {
        return Optional.ofNullable(resolvedSymbols.get(Objects.requireNonNull(node, "node")));
    }

    public Optional<Type> inferredType(SyntaxNode node) {
        return Optional.ofNullable(inferredTypes.get(Objects.requireNonNull(node, "node")));
    }

    public List<SemanticDiagnostic> diagnostics() {
        return diagnostics;
    }

    public SemanticModel withAdditionalDiagnostics(List<SemanticDiagnostic> additionalDiagnostics) {
        Objects.requireNonNull(additionalDiagnostics, "additionalDiagnostics");
        if (additionalDiagnostics.isEmpty())
            return this;

        List<SemanticDiagnostic> merged = new java.util.ArrayList<>(diagnostics.size() + additionalDiagnostics.size());
        merged.addAll(diagnostics);
        merged.addAll(additionalDiagnostics);
        return new SemanticModel(syntaxTree, rootScope, declaredSymbols, resolvedSymbols, inferredTypes, merged);
    }

    public static Builder builder(SyntaxTree syntaxTree, Scope rootScope) {
        return new Builder(syntaxTree, rootScope);
    }

    private static <T> Map<SyntaxNode, T> copyIdentityMap(Map<SyntaxNode, T> source, String name) {
        source = Objects.requireNonNull(source, name);
        Map<SyntaxNode, T> copy = new IdentityHashMap<>(source.size());
        copy.putAll(source);
        return Map.copyOf(copy);
    }

    public static final class Builder {
        private final SyntaxTree syntaxTree;
        private final Scope rootScope;
        private final Map<SyntaxNode, Symbol> declaredSymbols = new IdentityHashMap<>();
        private final Map<SyntaxNode, Symbol> resolvedSymbols = new IdentityHashMap<>();
        private final Map<SyntaxNode, Type> inferredTypes = new IdentityHashMap<>();
        private final List<SemanticDiagnostic> diagnostics = new java.util.ArrayList<>();

        private Builder(SyntaxTree syntaxTree, Scope rootScope) {
            this.syntaxTree = Objects.requireNonNull(syntaxTree, "syntaxTree");
            this.rootScope = Objects.requireNonNull(rootScope, "rootScope");
        }

        public Builder declare(SyntaxNode node, Symbol symbol) {
            declaredSymbols.put(Objects.requireNonNull(node, "node"), Objects.requireNonNull(symbol, "symbol"));
            return this;
        }

        public Builder resolve(SyntaxNode node, Symbol symbol) {
            resolvedSymbols.put(Objects.requireNonNull(node, "node"), Objects.requireNonNull(symbol, "symbol"));
            return this;
        }

        public Builder type(SyntaxNode node, Type type) {
            inferredTypes.put(Objects.requireNonNull(node, "node"), Objects.requireNonNull(type, "type"));
            return this;
        }

        public Builder diagnostic(SemanticDiagnostic diagnostic) {
            diagnostics.add(Objects.requireNonNull(diagnostic, "diagnostic"));
            return this;
        }

        public SemanticModel build() {
            return new SemanticModel(syntaxTree, rootScope, declaredSymbols, resolvedSymbols, inferredTypes, diagnostics);
        }
    }
}

package dev.railroadide.railroad.ide.diagnostics.inspections;

import dev.railroadide.railroad.ide.diagnostics.rules.java.JavaSemanticRules;
import dev.railroadide.railroad.ide.sst.impl.java.JavaSyntaxKinds;
import dev.railroadide.railroad.ide.sst.syntax.api.SyntaxNode;
import dev.railroadide.railroad.plugin.spi.inspection.JavaInspectionRule;
import dev.railroadide.railroad.plugin.spi.inspection.JavaInspectionRuleProvider;
import dev.railroadide.railroad.plugin.spi.inspection.JavaInspectionRuleReporter;
import dev.railroadide.railroad.plugin.spi.inspection.JavaRuleContext;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CoreCastConflictingWithInstanceofInspection implements JavaInspectionRuleProvider {
    public static final String ID = "railroad:cast-conflicting-with-instanceof";

    private static final List<JavaInspectionRule> RULES = List.of(
        new SimpleJavaInspectionRule(
            JavaSemanticRules.CAST_CONFLICTING_WITH_INSTANCEOF.id(),
            JavaSemanticRules.CAST_CONFLICTING_WITH_INSTANCEOF.defaultSeverity(),
            JavaSemanticRules.CAST_CONFLICTING_WITH_INSTANCEOF.messageTemplate(),
            Set.of("core", "casting"),
            CoreCastConflictingWithInstanceofInspection::reportCastConflictingWithInstanceof
        )
    );

    @Override
    public String id() {
        return ID;
    }

    @Override
    public List<JavaInspectionRule> rules() {
        return RULES;
    }

    private static void reportCastConflictingWithInstanceof(JavaRuleContext context, JavaInspectionRuleReporter reporter) {
        for (SyntaxNode ifNode : context.nodesOfKind(JavaSyntaxKinds.IF_STATEMENT.id())) {
            SyntaxNode condition = context.firstDirectExpressionChild(ifNode);
            if (condition == null)
                continue;

            InstanceofFact fact = extractPositiveInstanceofFact(context, condition);
            if (fact == null)
                continue;

            SyntaxNode thenBranch = thenBranchOf(context, ifNode);
            if (thenBranch == null)
                continue;

            for (SyntaxNode castNode : findDescendantCastExpressions(thenBranch)) {
                if (!castsSameVariable(context, castNode, fact.variableName()))
                    continue;

                SyntaxNode castTypeNode = context.directChild(castNode, JavaSyntaxKinds.TYPE_REFERENCE.id());
                if (castTypeNode == null)
                    continue;

                String castTypeName = context.resolveQualifiedTypeName(castTypeNode);
                if (castTypeName == null || castTypeName.isBlank())
                    continue;

                if (typesConflict(context, fact.testedTypeName(), castTypeName)) {
                    reporter.report(
                        castNode,
                        context.simpleTypeName(castTypeName),
                        context.simpleTypeName(fact.testedTypeName())
                    );
                }
            }
        }
    }

    private static @Nullable InstanceofFact extractPositiveInstanceofFact(JavaRuleContext context, SyntaxNode condition) {
        condition = context.unwrapTransparentExpression(condition);
        if (condition == null)
            return null;

        if (!JavaSyntaxKinds.INSTANCEOF_EXPRESSION.id().equals(condition.kind().id()))
            return null;

        List<SyntaxNode> expressions = context.directExpressionChildren(condition);
        if (expressions.isEmpty())
            return null;

        SyntaxNode testedExpression = expressions.getFirst();
        if (!JavaSyntaxKinds.NAME_EXPRESSION.id().equals(testedExpression.kind().id()))
            return null;

        String variableName = context.firstIdentifierLikeTokenText(testedExpression);
        if (variableName == null || variableName.isBlank())
            return null;

        SyntaxNode typeNode = instanceofTypeReference(context, condition);
        if (typeNode == null)
            return null;

        String testedTypeName = context.resolveQualifiedTypeName(typeNode);
        if (testedTypeName == null || testedTypeName.isBlank())
            return null;

        return new InstanceofFact(variableName, testedTypeName);
    }

    private static @Nullable SyntaxNode instanceofTypeReference(JavaRuleContext context, SyntaxNode instanceofNode) {
        SyntaxNode typeRef = context.directChild(instanceofNode, JavaSyntaxKinds.TYPE_REFERENCE.id());
        if (typeRef != null)
            return typeRef;

        SyntaxNode patternNode = context.directChild(instanceofNode, JavaSyntaxKinds.PATTERN.id());
        if (patternNode == null)
            return null;

        return findFirstDescendant(patternNode, JavaSyntaxKinds.TYPE_REFERENCE.id());
    }

    private static @Nullable SyntaxNode findFirstDescendant(SyntaxNode node, String kindId) {
        if (kindId.equals(node.kind().id()))
            return node;

        for (SyntaxNode child : node.children()) {
            SyntaxNode match = findFirstDescendant(child, kindId);
            if (match != null)
                return match;
        }

        return null;
    }

    private static @Nullable SyntaxNode thenBranchOf(JavaRuleContext context, SyntaxNode ifNode) {
        List<SyntaxNode> children = ifNode.children();
        boolean seenCondition = false;

        for (SyntaxNode child : children) {
            if (!seenCondition && context.isExpressionNode(child)) {
                seenCondition = true;
                continue;
            }

            if (seenCondition)
                return child;
        }

        return null;
    }

    private static List<SyntaxNode> findDescendantCastExpressions(SyntaxNode node) {
        List<SyntaxNode> casts = new ArrayList<>();
        collectDescendantCastExpressions(node, casts);
        return List.copyOf(casts);
    }

    private static void collectDescendantCastExpressions(SyntaxNode node, List<SyntaxNode> casts) {
        if (JavaSyntaxKinds.CAST_EXPRESSION.id().equals(node.kind().id())) {
            casts.add(node);
        }

        for (SyntaxNode child : node.children()) {
            collectDescendantCastExpressions(child, casts);
        }
    }

    private static boolean castsSameVariable(JavaRuleContext context, SyntaxNode castNode, String variableName) {
        for (SyntaxNode child : castNode.children()) {
            if (JavaSyntaxKinds.TYPE_REFERENCE.id().equals(child.kind().id()))
                continue;

            if (!context.isExpressionNode(child))
                continue;

            SyntaxNode expression = context.unwrapTransparentExpression(child);
            if (expression == null || !JavaSyntaxKinds.NAME_EXPRESSION.id().equals(expression.kind().id()))
                return false;

            String castedName = context.firstIdentifierLikeTokenText(expression);
            return variableName.equals(castedName);
        }

        return false;
    }

    private static boolean typesConflict(JavaRuleContext context, String instanceofType, String castType) {
        if (instanceofType.equals(castType))
            return false;

        if (context.isSubtype(castType, instanceofType))
            return false;

        //noinspection RedundantIfStatement - we want to be explicit about the logic here for readability
        if (context.isSubtype(instanceofType, castType))
            return false;

        return true;
    }

    private record InstanceofFact(String variableName, String testedTypeName) {
    }
}

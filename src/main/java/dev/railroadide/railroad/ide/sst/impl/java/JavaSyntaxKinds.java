package dev.railroadide.railroad.ide.sst.impl.java;

import dev.railroadide.railroad.ide.sst.syntax.api.SyntaxKind;

import java.util.EnumMap;
import java.util.Map;

final class JavaSyntaxKinds {
    static final SyntaxKind COMPILATION_UNIT = SyntaxKind.of("JAVA_COMPILATION_UNIT");
    static final SyntaxKind PACKAGE_DECLARATION = SyntaxKind.of("JAVA_PACKAGE_DECLARATION");
    static final SyntaxKind IMPORT_DECLARATION = SyntaxKind.of("JAVA_IMPORT_DECLARATION");
    static final SyntaxKind IMPORT_TARGET = SyntaxKind.of("JAVA_IMPORT_TARGET");
    static final SyntaxKind MODULE_DECLARATION = SyntaxKind.of("JAVA_MODULE_DECLARATION");
    static final SyntaxKind MODULE_BODY = SyntaxKind.of("JAVA_MODULE_BODY");
    static final SyntaxKind MODULE_REQUIRES_DIRECTIVE = SyntaxKind.of("JAVA_MODULE_REQUIRES_DIRECTIVE");
    static final SyntaxKind MODULE_EXPORTS_DIRECTIVE = SyntaxKind.of("JAVA_MODULE_EXPORTS_DIRECTIVE");
    static final SyntaxKind MODULE_OPENS_DIRECTIVE = SyntaxKind.of("JAVA_MODULE_OPENS_DIRECTIVE");
    static final SyntaxKind MODULE_USES_DIRECTIVE = SyntaxKind.of("JAVA_MODULE_USES_DIRECTIVE");
    static final SyntaxKind MODULE_PROVIDES_DIRECTIVE = SyntaxKind.of("JAVA_MODULE_PROVIDES_DIRECTIVE");
    static final SyntaxKind MODULE_UNKNOWN_DIRECTIVE = SyntaxKind.of("JAVA_MODULE_UNKNOWN_DIRECTIVE");
    static final SyntaxKind QUALIFIED_NAME = SyntaxKind.of("JAVA_QUALIFIED_NAME");
    static final SyntaxKind ANNOTATION = SyntaxKind.of("JAVA_ANNOTATION");
    static final SyntaxKind TYPE_DECLARATION = SyntaxKind.of("JAVA_TYPE_DECLARATION");
    static final SyntaxKind CLASS_DECLARATION = SyntaxKind.of("JAVA_CLASS_DECLARATION");
    static final SyntaxKind INTERFACE_DECLARATION = SyntaxKind.of("JAVA_INTERFACE_DECLARATION");
    static final SyntaxKind ENUM_DECLARATION = SyntaxKind.of("JAVA_ENUM_DECLARATION");
    static final SyntaxKind ANNOTATION_TYPE_DECLARATION = SyntaxKind.of("JAVA_ANNOTATION_TYPE_DECLARATION");
    static final SyntaxKind RECORD_DECLARATION = SyntaxKind.of("JAVA_RECORD_DECLARATION");
    static final SyntaxKind EMPTY_TYPE_DECLARATION = SyntaxKind.of("JAVA_EMPTY_TYPE_DECLARATION");
    static final SyntaxKind CLASS_BODY = SyntaxKind.of("JAVA_CLASS_BODY");
    static final SyntaxKind INTERFACE_BODY = SyntaxKind.of("JAVA_INTERFACE_BODY");
    static final SyntaxKind ENUM_BODY = SyntaxKind.of("JAVA_ENUM_BODY");
    static final SyntaxKind ANNOTATION_TYPE_BODY = SyntaxKind.of("JAVA_ANNOTATION_TYPE_BODY");
    static final SyntaxKind RECORD_BODY = SyntaxKind.of("JAVA_RECORD_BODY");
    static final SyntaxKind TYPE_MEMBER = SyntaxKind.of("JAVA_TYPE_MEMBER");
    static final SyntaxKind ANNOTATION_TYPE_MEMBER = SyntaxKind.of("JAVA_ANNOTATION_TYPE_MEMBER");
    static final SyntaxKind RECORD_COMPACT_CONSTRUCTOR = SyntaxKind.of("JAVA_RECORD_COMPACT_CONSTRUCTOR");
    static final SyntaxKind ENUM_CONSTANT = SyntaxKind.of("JAVA_ENUM_CONSTANT");
    static final SyntaxKind FIELD_DECLARATION = SyntaxKind.of("JAVA_FIELD_DECLARATION");
    static final SyntaxKind VARIABLE_DECLARATOR = SyntaxKind.of("JAVA_VARIABLE_DECLARATOR");
    static final SyntaxKind METHOD_DECLARATION = SyntaxKind.of("JAVA_METHOD_DECLARATION");
    static final SyntaxKind CONSTRUCTOR_DECLARATION = SyntaxKind.of("JAVA_CONSTRUCTOR_DECLARATION");
    static final SyntaxKind PARAMETER_LIST = SyntaxKind.of("JAVA_PARAMETER_LIST");
    static final SyntaxKind PARAMETER = SyntaxKind.of("JAVA_PARAMETER");
    static final SyntaxKind THROWS_CLAUSE = SyntaxKind.of("JAVA_THROWS_CLAUSE");
    static final SyntaxKind STATIC_INITIALIZER = SyntaxKind.of("JAVA_STATIC_INITIALIZER");
    static final SyntaxKind INSTANCE_INITIALIZER = SyntaxKind.of("JAVA_INSTANCE_INITIALIZER");
    static final SyntaxKind RECORD_HEADER = SyntaxKind.of("JAVA_RECORD_HEADER");
    static final SyntaxKind RECORD_COMPONENT = SyntaxKind.of("JAVA_RECORD_COMPONENT");
    static final SyntaxKind TYPE_PARAMETERS = SyntaxKind.of("JAVA_TYPE_PARAMETERS");
    static final SyntaxKind TYPE_ARGUMENTS = SyntaxKind.of("JAVA_TYPE_ARGUMENTS");
    static final SyntaxKind TYPE_PARAMETER = SyntaxKind.of("JAVA_TYPE_PARAMETER");
    static final SyntaxKind TYPE_BOUND = SyntaxKind.of("JAVA_TYPE_BOUND");
    static final SyntaxKind WILDCARD_TYPE = SyntaxKind.of("JAVA_WILDCARD_TYPE");
    static final SyntaxKind DIAMOND_TYPE_ARGUMENTS = SyntaxKind.of("JAVA_DIAMOND_TYPE_ARGUMENTS");
    static final SyntaxKind TYPE_REFERENCE = SyntaxKind.of("JAVA_TYPE_REFERENCE");
    static final SyntaxKind INTERSECTION_TYPE_REFERENCE = SyntaxKind.of("JAVA_INTERSECTION_TYPE_REFERENCE");
    static final SyntaxKind UNION_TYPE_REFERENCE = SyntaxKind.of("JAVA_UNION_TYPE_REFERENCE");
    static final SyntaxKind ARRAY_DIMENSION = SyntaxKind.of("JAVA_ARRAY_DIMENSION");
    static final SyntaxKind EXTENDS_CLAUSE = SyntaxKind.of("JAVA_EXTENDS_CLAUSE");
    static final SyntaxKind IMPLEMENTS_CLAUSE = SyntaxKind.of("JAVA_IMPLEMENTS_CLAUSE");
    static final SyntaxKind PERMITS_CLAUSE = SyntaxKind.of("JAVA_PERMITS_CLAUSE");
    static final SyntaxKind STATEMENT = SyntaxKind.of("JAVA_STATEMENT");
    static final SyntaxKind BLOCK = SyntaxKind.of("JAVA_BLOCK");
    static final SyntaxKind EMPTY_STATEMENT = SyntaxKind.of("JAVA_EMPTY_STATEMENT");
    static final SyntaxKind EXPRESSION_STATEMENT = SyntaxKind.of("JAVA_EXPRESSION_STATEMENT");
    static final SyntaxKind LOCAL_VARIABLE_DECLARATION_STATEMENT = SyntaxKind.of("JAVA_LOCAL_VARIABLE_DECLARATION_STATEMENT");
    static final SyntaxKind IF_STATEMENT = SyntaxKind.of("JAVA_IF_STATEMENT");
    static final SyntaxKind SWITCH_STATEMENT = SyntaxKind.of("JAVA_SWITCH_STATEMENT");
    static final SyntaxKind SWITCH_RULE = SyntaxKind.of("JAVA_SWITCH_RULE");
    static final SyntaxKind SWITCH_LABEL = SyntaxKind.of("JAVA_SWITCH_LABEL");
    static final SyntaxKind SWITCH_CASE_ITEM = SyntaxKind.of("JAVA_SWITCH_CASE_ITEM");
    static final SyntaxKind PATTERN_GUARD = SyntaxKind.of("JAVA_PATTERN_GUARD");
    static final SyntaxKind WHILE_STATEMENT = SyntaxKind.of("JAVA_WHILE_STATEMENT");
    static final SyntaxKind DO_WHILE_STATEMENT = SyntaxKind.of("JAVA_DO_WHILE_STATEMENT");
    static final SyntaxKind FOR_STATEMENT = SyntaxKind.of("JAVA_FOR_STATEMENT");
    static final SyntaxKind BASIC_FOR_STATEMENT = SyntaxKind.of("JAVA_BASIC_FOR_STATEMENT");
    static final SyntaxKind ENHANCED_FOR_STATEMENT = SyntaxKind.of("JAVA_ENHANCED_FOR_STATEMENT");
    static final SyntaxKind TRY_STATEMENT = SyntaxKind.of("JAVA_TRY_STATEMENT");
    static final SyntaxKind TRY_RESOURCE = SyntaxKind.of("JAVA_TRY_RESOURCE");
    static final SyntaxKind CATCH_CLAUSE = SyntaxKind.of("JAVA_CATCH_CLAUSE");
    static final SyntaxKind FINALLY_CLAUSE = SyntaxKind.of("JAVA_FINALLY_CLAUSE");
    static final SyntaxKind SYNCHRONIZED_STATEMENT = SyntaxKind.of("JAVA_SYNCHRONIZED_STATEMENT");
    static final SyntaxKind RETURN_STATEMENT = SyntaxKind.of("JAVA_RETURN_STATEMENT");
    static final SyntaxKind THROW_STATEMENT = SyntaxKind.of("JAVA_THROW_STATEMENT");
    static final SyntaxKind BREAK_STATEMENT = SyntaxKind.of("JAVA_BREAK_STATEMENT");
    static final SyntaxKind CONTINUE_STATEMENT = SyntaxKind.of("JAVA_CONTINUE_STATEMENT");
    static final SyntaxKind ASSERT_STATEMENT = SyntaxKind.of("JAVA_ASSERT_STATEMENT");
    static final SyntaxKind YIELD_STATEMENT = SyntaxKind.of("JAVA_YIELD_STATEMENT");
    static final SyntaxKind LABELED_STATEMENT = SyntaxKind.of("JAVA_LABELED_STATEMENT");
    static final SyntaxKind EXPRESSION = SyntaxKind.of("JAVA_EXPRESSION");
    static final SyntaxKind LAMBDA_EXPRESSION = SyntaxKind.of("JAVA_LAMBDA_EXPRESSION");
    static final SyntaxKind LAMBDA_PARAMETERS = SyntaxKind.of("JAVA_LAMBDA_PARAMETERS");
    static final SyntaxKind LAMBDA_PARAMETER = SyntaxKind.of("JAVA_LAMBDA_PARAMETER");
    static final SyntaxKind LAMBDA_BODY = SyntaxKind.of("JAVA_LAMBDA_BODY");
    static final SyntaxKind ASSIGNMENT_EXPRESSION = SyntaxKind.of("JAVA_ASSIGNMENT_EXPRESSION");
    static final SyntaxKind CONDITIONAL_EXPRESSION = SyntaxKind.of("JAVA_CONDITIONAL_EXPRESSION");
    static final SyntaxKind BINARY_EXPRESSION = SyntaxKind.of("JAVA_BINARY_EXPRESSION");
    static final SyntaxKind INSTANCEOF_EXPRESSION = SyntaxKind.of("JAVA_INSTANCEOF_EXPRESSION");
    static final SyntaxKind UNARY_EXPRESSION = SyntaxKind.of("JAVA_UNARY_EXPRESSION");
    static final SyntaxKind CAST_EXPRESSION = SyntaxKind.of("JAVA_CAST_EXPRESSION");
    static final SyntaxKind POSTFIX_EXPRESSION = SyntaxKind.of("JAVA_POSTFIX_EXPRESSION");
    static final SyntaxKind PRIMARY_EXPRESSION = SyntaxKind.of("JAVA_PRIMARY_EXPRESSION");
    static final SyntaxKind PARENTHESIZED_EXPRESSION = SyntaxKind.of("JAVA_PARENTHESIZED_EXPRESSION");
    static final SyntaxKind NAME_EXPRESSION = SyntaxKind.of("JAVA_NAME_EXPRESSION");
    static final SyntaxKind THIS_EXPRESSION = SyntaxKind.of("JAVA_THIS_EXPRESSION");
    static final SyntaxKind SUPER_EXPRESSION = SyntaxKind.of("JAVA_SUPER_EXPRESSION");
    static final SyntaxKind FIELD_ACCESS_EXPRESSION = SyntaxKind.of("JAVA_FIELD_ACCESS_EXPRESSION");
    static final SyntaxKind ARRAY_ACCESS_EXPRESSION = SyntaxKind.of("JAVA_ARRAY_ACCESS_EXPRESSION");
    static final SyntaxKind METHOD_INVOCATION_EXPRESSION = SyntaxKind.of("JAVA_METHOD_INVOCATION_EXPRESSION");
    static final SyntaxKind ARGUMENT_LIST = SyntaxKind.of("JAVA_ARGUMENT_LIST");
    static final SyntaxKind METHOD_REFERENCE_EXPRESSION = SyntaxKind.of("JAVA_METHOD_REFERENCE_EXPRESSION");
    static final SyntaxKind CLASS_INSTANCE_CREATION_EXPRESSION = SyntaxKind.of("JAVA_CLASS_INSTANCE_CREATION_EXPRESSION");
    static final SyntaxKind ANONYMOUS_CLASS_BODY = SyntaxKind.of("JAVA_ANONYMOUS_CLASS_BODY");
    static final SyntaxKind ARRAY_CREATION_EXPRESSION = SyntaxKind.of("JAVA_ARRAY_CREATION_EXPRESSION");
    static final SyntaxKind ARRAY_INITIALIZER_EXPRESSION = SyntaxKind.of("JAVA_ARRAY_INITIALIZER_EXPRESSION");
    static final SyntaxKind CLASS_LITERAL_EXPRESSION = SyntaxKind.of("JAVA_CLASS_LITERAL_EXPRESSION");
    static final SyntaxKind SWITCH_EXPRESSION = SyntaxKind.of("JAVA_SWITCH_EXPRESSION");
    static final SyntaxKind LITERAL_EXPRESSION = SyntaxKind.of("JAVA_LITERAL_EXPRESSION");
    static final SyntaxKind PATTERN = SyntaxKind.of("JAVA_PATTERN");
    static final SyntaxKind ERROR = SyntaxKind.of("JAVA_ERROR");

    private static final Map<JavaTokenType, SyntaxKind> TOKEN_KINDS = createTokenKinds();
    private static final Map<JavaTokenType, SyntaxKind> MISSING_TOKEN_KINDS = createMissingTokenKinds();

    private JavaSyntaxKinds() {
    }

    static SyntaxKind tokenKind(JavaTokenType tokenType) {
        return TOKEN_KINDS.get(tokenType);
    }

    static SyntaxKind missingTokenKind(JavaTokenType tokenType) {
        return MISSING_TOKEN_KINDS.getOrDefault(tokenType, SyntaxKind.MISSING_TOKEN);
    }

    private static Map<JavaTokenType, SyntaxKind> createTokenKinds() {
        Map<JavaTokenType, SyntaxKind> tokenKinds = new EnumMap<>(JavaTokenType.class);
        for (JavaTokenType tokenType : JavaTokenType.values()) {
            tokenKinds.put(tokenType, SyntaxKind.of("JAVA_TOKEN_" + tokenType.name()));
        }

        return Map.copyOf(tokenKinds);
    }

    private static Map<JavaTokenType, SyntaxKind> createMissingTokenKinds() {
        Map<JavaTokenType, SyntaxKind> tokenKinds = new EnumMap<>(JavaTokenType.class);
        for (JavaTokenType tokenType : JavaTokenType.values()) {
            tokenKinds.put(tokenType, SyntaxKind.of("JAVA_MISSING_" + tokenType.name()));
        }

        return Map.copyOf(tokenKinds);
    }
}

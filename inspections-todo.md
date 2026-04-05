# Java Inspections TODO

This file is a curated backlog of JetBrains Inspectopedia inspections for core Java. It is intentionally centered on the Java language, type system, control flow, initialization, modules, exceptions, visibility, numeric semantics, serialization, and other standard-library-adjacent correctness checks, while excluding most framework-specific areas like JUnit, TestNG, Lombok, Hibernate-specific style rules, and similar ecosystem-only buckets.

Primary source: JetBrains Inspectopedia Java catalog and category pages, current as of March-April 2026.

Status legend:
- `[x]` implemented in the current Railroad inspection engine
- `[-]` partially implemented or only covered for a narrower subset of cases
- `[ ]` not implemented yet

Relevant source pages:
- https://www.jetbrains.com/help/inspectopedia/Java.html
- https://www.jetbrains.com/help/inspectopedia/Java-Abstraction-issues.html
- https://www.jetbrains.com/help/inspectopedia/Java-Assignment-issues.html
- https://www.jetbrains.com/help/inspectopedia/Java-Class-structure.html
- https://www.jetbrains.com/help/inspectopedia/Java-Compiler-issues.html
- https://www.jetbrains.com/help/inspectopedia/Java-Control-flow-issues.html
- https://www.jetbrains.com/help/inspectopedia/Java-Data-flow.html
- https://www.jetbrains.com/help/inspectopedia/Java-Declaration-redundancy.html
- https://www.jetbrains.com/help/inspectopedia/Java-Encapsulation.html
- https://www.jetbrains.com/help/inspectopedia/Java-Error-handling.html
- https://www.jetbrains.com/help/inspectopedia/Java-Imports.html
- https://www.jetbrains.com/help/inspectopedia/Java-Inheritance-issues.html
- https://www.jetbrains.com/help/inspectopedia/Java-Initialization.html
- https://www.jetbrains.com/help/inspectopedia/Java-Java-language-level-issues.html
- https://www.jetbrains.com/help/inspectopedia/Java-Numeric-issues.html
- https://www.jetbrains.com/help/inspectopedia/Java-Resource-management.html
- https://www.jetbrains.com/help/inspectopedia/Java-Serialization-issues.html
- https://www.jetbrains.com/help/inspectopedia/Java-Visibility.html
- https://www.jetbrains.com/help/inspectopedia/Java-Verbose-or-redundant-code-constructs.html
- https://www.jetbrains.com/help/inspectopedia/Java-Probable-bugs.html

## Railroad semantic baseline already implemented

These are core semantic/compiler-style checks already present in the codebase, even when they do not map 1:1 to a single Inspectopedia label in the sections below.

- [x] `Duplicate declaration` - Detects duplicate symbols declared in the same effective scope.
- [x] `Unresolved name` - Detects identifiers that fail semantic resolution.
- [x] `Duplicate import` - Detects repeated imports of the same target.
- [x] `Ambiguous import` - Detects conflicting imports with the same simple name.
- [x] `Unresolved import` - Detects imports whose type/package/member target cannot be resolved.
- [x] `Unresolved type` - Detects type references that do not resolve semantically.
- [x] `Unresolved member` - Detects field/member references that do not resolve.
- [x] `Unresolved call` - Detects method invocations and constructor calls that do not resolve.
- [x] `Inaccessible type` - Detects type references that violate Java accessibility rules.
- [x] `Inaccessible member` - Detects member references that violate Java accessibility rules.
- [x] `Inaccessible call` - Detects calls to inaccessible methods or constructors.
- [x] `Invalid inheritance` - Detects illegal `extends`/`implements` relationships.
- [x] `Missing implementation` - Detects concrete types that fail to implement inherited abstract methods.
- [x] `Invalid override` - Detects override errors involving final/static/access/return/throws compatibility.
- [x] `Illegal modifier usage` - Detects invalid modifier combinations and placement.
- [x] `Missing return on some paths` - Detects non-void methods that do not return on every path.
- [x] `Incompatible assignment` - Detects assignments and initializers with incompatible types.
- [x] `Variable might not have been initialized` - Detects definite-assignment failures for local variables.
- [x] `Illegal final assignment` - Detects writes to final variables in illegal contexts.
- [x] `Final field might not be initialized` - Detects final fields that are not definitely assigned.

## Abstraction issues

- [ ] `'instanceof' check for 'this'` - Detect `this instanceof ...` or equivalent class checks that usually indicate misplaced polymorphism.
- [ ] `'Optional' used as field or parameter type` - Flag `Optional` as a field or parameter type where it complicates APIs or object state.
- [ ] `'public' method not exposed in interface` - Find public class methods that are not represented in an interface contract.
- [ ] `'public' method with 'boolean' parameter` - Detect public APIs that use booleans as behavior switches.
- [ ] `Chain of 'instanceof' checks` - Find if-else type dispatch chains that should likely become polymorphism or pattern matching.
- [ ] `Class references one of its subclasses` - Detect superclass knowledge of subclasses, which inverts the abstraction direction.
- [ ] `Collection declared by class, not interface` - Prefer `List`/`Set`/`Map`-style abstractions over concrete collection types in declarations.
- [ ] `Feature envy` - Find code that mostly manipulates another object's data instead of its own.
- [ ] `Interface method clashes with method in 'Object'` - Detect interface methods that conflict with `Object` protocol expectations.
- [ ] `Magic number` - Identify unnamed numeric literals that should become named constants.
- [ ] `Overly strong type cast` - Detect casts stronger than necessary for the operations performed.
- [ ] `Private method only used from inner class` - Find private helpers that are effectively scoped for inner-type use only.
- [ ] `Static member only used from one other class` - Detect static members whose usage suggests relocation.
- [ ] `Type may be weakened` - Replace concrete or overly-specific types with weaker abstractions where valid.
- [ ] `Use of concrete class` - Detect APIs and declarations that depend on implementations instead of interfaces.

## Assignment issues

- [ ] `'null' assignment` - Report assignments that explicitly write `null` outside a declaration.
- [ ] `Assignment can be replaced with operator assignment` - Detect `x = x + y` forms that can become compound assignment.
- [ ] `Assignment to 'catch' block parameter` - Flag mutation of a caught exception variable.
- [ ] `Assignment to 'for' loop parameter` - Detect loop-variable mutation inside enhanced-for bodies.
- [ ] `Assignment to lambda parameter` - Flag writes to lambda parameters.
- [ ] `Assignment to method parameter` - Detect mutation of incoming method parameters.
- [ ] `Assignment to static field from instance context` - Find instance methods that mutate class state.
- [ ] `Assignment used as condition` - Detect assignments embedded in `if`, `while`, `for`, `do`, or ternary conditions.
- [ ] `Constructor assigns value to field defined in superclass` - Flag subclass constructors mutating inherited state directly.
- [ ] `Nested assignment` - Detect assignment expressions used inside larger expressions.
- [ ] `Result of '++' or '--' used` - Flag pre/post increment results used as expression values rather than standalone statements.

## Class structure

- [ ] `'private' method declared 'final'` - Detect meaningless `final` on private methods.
- [ ] `'public' constructor can be replaced with factory method` - Identify constructors where a factory-style API may be preferable.
- [ ] `'static' method declared 'final'` - Detect meaningless `final` on static methods.
- [ ] `'static', non-'final' field` - Flag mutable static state.
- [ ] `Abstract 'class' may be 'interface'` - Identify abstract classes with interface-like shape.
- [ ] `Anonymous class can be replaced with inner class` - Detect anonymous classes better modeled as named nested types.
- [ ] `Class is closed to inheritance` - Track final classes and inheritance restrictions.
- [-] `Class name differs from file name` - Implemented for public top-level types; broader file/type structural cases are still missing.
- [ ] `Class with only 'private' constructors should be declared 'final'` - Flag utility-like classes that still allow subclassing.
- [ ] `Constant declared in 'abstract' class` - Detect public constants living in abstract classes.
- [ ] `Constant declared in interface` - Flag interface constants as a structure smell.
- [ ] `Empty class` - Detect empty type declarations and empty Java files.
- [ ] `Field can be local variable` - Find fields whose lifetime can be reduced to a local variable.
- [ ] `Inner class of interface` - Detect nested classes declared inside interfaces.
- [ ] `Interface may be annotated as '@FunctionalInterface'` - Find SAM interfaces that should declare intent explicitly.
- [ ] `Local class` - Flag local class declarations inside methods or blocks.
- [ ] `Marker interface` - Detect interfaces with no members, often replaceable with annotations or other mechanisms.
- [-] `Method can't be overridden` - Partially covered via invalid-override checks, especially final/static/access-signature conflicts.
- [ ] `Multiple top level classes in single file` - Flag multiple top-level types in one compilation unit.
- [ ] `No-op method in 'abstract' class` - Find empty methods in abstract base types that may hide required behavior.
- [ ] `Non-'static' initializer` - Detect instance initializer blocks.
- [ ] `Non-final field in 'enum'` - Flag mutable enum instance state.
- [ ] `Singleton` - Detect singleton implementations for review and intentionality.
- [ ] `Utility class` - Identify classes made entirely of static utilities.
- [ ] `Utility class can be 'enum'` - Detect utility classes that can use enum-singleton mechanics.
- [ ] `Utility class is not 'final'` - Flag utility classes that still permit subclassing.
- [ ] `Utility class with 'public' constructor` - Detect utility types that can be instantiated.
- [ ] `Utility class without 'private' constructor` - Require constructors to prevent instantiation.
- [ ] `Value passed as parameter never read` - Find parameters whose incoming value is ignored and replaced.

## Compiler issues

- [x] `Illegal modifier usage` - Detects invalid modifier combinations and placements across types, fields, methods, constructors, locals, parameters, and record components.
- [ ] `Javac quirks` - Mirror known `javac` incompatibilities, performance issues, and corner cases.
- [ ] `Preview Feature warning` - Flag use of preview language or API features.
- [ ] `Unchecked warning` - Surface code that triggers unchecked generic warnings in `javac`.
- [ ] `Value-based warnings` - Detect illegal synchronization and similar misuse of value-based classes.

## Control flow issues

- [-] `'break' statement` - Implemented for illegal placement and unresolved labels, not for broader style/simplification heuristics.
- [-] `'break' statement with label` - Implemented for label resolution validity, not for readability/redundancy heuristics.
- [-] `'continue' statement` - Implemented for illegal placement and bad targets, not for broader style/simplification heuristics.
- [-] `'continue' statement with label` - Implemented for label resolution and loop-target validity, not for redundancy heuristics.
- [ ] `'default' not last case in 'switch'` - Enforce expected `default` placement in `switch`.
- [ ] `'for' loop may be replaced by 'while' loop` - Find `for` loops with no init/update sections.
- [ ] `'for' loop with missing components` - Detect sparse `for` headers that obscure loop intent.
- [ ] `'if' statement with identical branches or common parts` - Find branch bodies that can be merged or extracted.
- [ ] `'if' statement with negated condition` - Detect `if (!cond) ... else ...` shapes that can be inverted.
- [ ] `'if' statement with too many branches` - Flag very large branch chains.
- [ ] `'switch' statement` - Track ordinary `switch` usage where alternatives may be preferable.
- [ ] `'switch' statement with too low of a branch density` - Detect `switch` constructs with too little actual dispatch value.
- [ ] `'switch' statement without 'default' branch` - Flag non-exhaustive switches.
- [ ] `'while' can be replaced with 'do while'` - Detect loops whose body must run at least once.
- [ ] `Assertion can be replaced with 'if' statement` - Find asserts that should be enforced with explicit runtime checks.
- [ ] `Boolean expression can be replaced with conditional expression` - Simplify verbose boolean logic.
- [ ] `Common subexpression can be extracted from 'switch'` - Pull repeated branch logic around a `switch`.
- [ ] `Conditional break inside loop` - Replace leading/trailing conditional breaks with loop conditions when clearer.
- [ ] `Conditional can be pushed inside branch expression` - Simplify duplicated ternary branch structure.
- [ ] `Conditional expression` - Flag ternaries where statement form may be clearer.
- [ ] `Conditional expression with identical branches` - Detect ternaries that produce the same result on both sides.
- [ ] `Conditional expression with negated condition` - Simplify negated ternary conditions.
- [ ] `Constant conditional expression` - Detect conditions known at compile/data-flow time.
- [ ] `Double negation` - Simplify `!!expr` style logic.
- [ ] `Duplicate condition` - Find repeated boolean conditions in the same decision logic.
- [ ] `Enum 'switch' statement that misses case` - Detect non-exhaustive enum switches.
- [ ] `Expression can be factorized` - Identify algebraically factorable expressions.
- [ ] `Fallthrough in 'switch' statement` - Report implicit or suspicious fallthrough between cases.
- [ ] `Idempotent loop body` - Detect loops whose later iterations do no additional useful work.
- [ ] `Infinite loop statement` - Flag loops that only terminate by exception or external exit.
- [ ] `Labeled statement` - Detect labels that make flow harder to reason about.
- [x] `Missing return on some control-flow paths` - Detects non-void methods whose bodies do not definitely return or throw on every path.
- [ ] `Local variable used and declared in different 'switch' branches` - Flag branch-local variable leakage across cases.
- [ ] `Loop statement that does not loop` - Detect loops that execute at most once.
- [ ] `Loop variable not updated inside loop` - Flag loop conditions that depend on unchanged values.
- [ ] `Loop with implicit termination condition` - Detect `while(true)`-style loops with hidden exit logic.
- [ ] `Maximum 'switch' branches` - Flag switches that are too large.
- [ ] `Minimum 'switch' branches` - Detect tiny switches better modeled as `if` chains.
- [ ] `Negated conditional expression` - Flag ternaries negated from the outside.
- [ ] `Negated equality expression` - Simplify negated equality checks.
- [ ] `Nested 'switch' statement` - Detect nested switch structures.
- [ ] `Nested conditional expression` - Flag nested ternaries.
- [ ] `Overly complex boolean expression` - Detect boolean expressions with too many terms.
- [ ] `Pointless 'indexOf()' comparison` - Find redundant `indexOf` comparison patterns.
- [ ] `Pointless boolean expression` - Detect logic that always reduces to a simpler expression.
- [ ] `Redundant 'else'` - Remove `else` after a terminal branch.
- [ ] `Redundant 'if' statement` - Collapse `if` to direct assignment, return, or assert when possible.
- [ ] `Simplifiable boolean expression` - Reduce boolean logic to a simpler equivalent.
- [ ] `Simplifiable conditional expression` - Reduce ternaries to simpler forms.
- [ ] `Statement can be replaced with 'assert' or 'Objects.requireNonNull'` - Detect guard statements expressible by assertion or null-check helpers.
- [ ] `Unnecessary 'null' check before method call` - Remove null checks before methods already null-safe or deterministically false.

## Data flow

- [ ] `Boolean method is always inverted` - Detect boolean-returning methods that callers nearly always negate.
- [ ] `Boolean variable is always inverted` - Detect booleans that are consistently used negated.
- [ ] `Law of Demeter` - Find call chains that reach too far through object graphs.
- [ ] `Negatively named boolean variable` - Flag boolean names like `disabled` or `notReady` that invert readability.
- [ ] `Redundant local variable` - Remove locals that add no value beyond the source expression.
- [ ] `Reuse of local variable` - Flag locals repurposed for unrelated values.
- [ ] `Scope of variable is too broad` - Narrow declarations to the smallest valid scope.
- [ ] `Use of variable whose value is known to be constant` - Detect places where constant propagation exposes unnecessary indirection.

## Declaration redundancy

- [ ] `'final' method in 'final' class` - Detect redundant `final` on methods in a final class.
- [ ] `'protected' member in 'final' class` - Flag protected members that cannot actually be inherited.
- [ ] `@SafeVarargs is not applicable to reifiable types` - Detect pointless `@SafeVarargs` on reifiable varargs.
- [ ] `Access static member via instance reference` - Require class qualification for static members.
- [ ] `Declaration access can be weaker` - Narrow visibility where usage permits.
- [ ] `Declaration can have 'final' modifier` - Add `final` where the declaration is never varied.
- [ ] `Default annotation parameter value` - Remove annotation elements explicitly set to their default.
- [ ] `Duplicate throws` - Detect repeated exception types in a `throws` list.
- [ ] `Empty class initializer` - Remove empty initializer blocks.
- [ ] `Functional expression can be folded` - Replace lambdas or method refs with a simpler direct form.
- [ ] `Java module definition problems` - Detect miscellaneous `module-info.java` declaration issues.
- [ ] `Method always returns the same value` - Find constant-return methods.
- [ ] `Method can be made 'void'` - Detect methods whose return values are never used.
- [ ] `Method parameter always has the same value` - Detect effectively constant parameters.
- [ ] `Redundant 'close()'` - Remove explicit `close()` inside try-with-resources.
- [ ] `Redundant 'requires' directive in module-info` - Remove unnecessary module dependencies.
- [ ] `Redundant 'throws' clause` - Remove declared exceptions that are never thrown.
- [ ] `Redundant lambda parameter types` - Omit lambda parameter types when type inference suffices.
- [ ] `Redundant record constructor` - Remove record constructors that duplicate the canonical default behavior.
- [ ] `Trivial usage of functional expression` - Inline immediately-invoked functional expressions.
- [ ] `Unnecessary module dependency` - Remove unused module requirements.
- [ ] `Unused declaration` - Detect dead classes, methods, and fields.
- [ ] `Unused label` - Remove labels never targeted by control flow.
- [ ] `Variable is assigned to itself` - Flag self-assignment.

## Encapsulation

- [ ] `'public' field` - Detect publicly exposed mutable or structural state.
- [ ] `'public' nested class` - Flag broad nested-type exposure.
- [ ] `Accessing a non-public field of another object` - Detect access to another instance's protected/private-ish internals.
- [ ] `Assignment or return of field with mutable type` - Flag leaking or storing mutable references unsafely.
- [ ] `Package-visible field` - Detect package-private fields.
- [ ] `Package-visible nested class` - Detect package-private nested types.
- [ ] `Protected field` - Flag protected fields that widen mutation surface.
- [ ] `Protected nested class` - Flag protected nested type exposure.

## Error handling

- [x] `Empty 'catch' block` - Detects `catch` clauses with no statements.
- [ ] `'continue' or 'break' inside 'finally' block` - Detect control-flow escapes from `finally`.
- [ ] `'Error' not rethrown` - Flag catching `Error` without rethrowing it.
- [ ] `'finally' block which can not complete normally` - Detect `finally` blocks that terminate via return, throw, break, continue, or yield.
- [ ] `'instanceof' on 'catch' parameter` - Flag type switching on a caught exception.
- [ ] `'null' thrown` - Detect `throw null`.
- [ ] `'return' inside 'finally' block` - Forbid returns from `finally`.
- [ ] `'ThreadDeath' not rethrown` - Flag swallowing `ThreadDeath`.
- [ ] `'throw' caught by containing 'try' statement` - Detect throws that are immediately caught by the surrounding try.
- [ ] `'throw' inside 'catch' block which ignores the caught exception` - Flag rethrows that discard original failure context.
- [ ] `'throw' inside 'finally' block` - Detect throws from `finally`.
- [ ] `Catch block may ignore exception` - Find empty or effectively swallowing catches.
- [ ] `Caught exception is immediately rethrown` - Detect useless catch/rethrow blocks.
- [ ] `Checked exception class` - Track checked-exception type declarations.
- [ ] `Class directly extends 'Throwable'` - Flag custom types extending `Throwable` instead of `Exception`/`RuntimeException`.
- [ ] `Empty 'finally' block` - Remove no-op `finally` blocks.
- [ ] `Empty 'try' block` - Remove empty `try` or try-with-resources bodies.
- [ ] `Exception constructor called without arguments` - Prefer richer exception construction with message and/or cause.
- [ ] `Nested 'try' statement` - Detect nested try structures that obscure error paths.
- [ ] `Non-final field of 'Exception' class` - Flag mutable exception state.
- [ ] `Overly broad 'catch' block` - Narrow overly generic catch parameters.
- [ ] `Overly broad 'throws' clause` - Narrow overly generic declared exceptions.
- [ ] `Prohibited 'Exception' caught` - Detect disallowed catch targets like generic `Exception` in configured contexts.
- [ ] `Prohibited exception declared` - Flag disallowed exceptions in method signatures.
- [ ] `Prohibited exception thrown` - Flag disallowed exception types in `throw`.
- [ ] `Throwable supplier never returns a value` - Detect `orElseThrow` suppliers that throw instead of returning the throwable.
- [ ] `Unchecked 'Exception' class` - Track runtime-exception type declarations.
- [ ] `Unchecked exception declared in 'throws' clause` - Remove unnecessary unchecked exceptions from method signatures.
- [ ] `Unnecessary call to 'Throwable.initCause()'` - Prefer constructors that directly accept the cause.

## Imports

- [x] `Ambiguous import` - Detects imported types with the same simple name that conflict.
- [x] `Duplicate import` - Detects repeated imports of the same symbol.
- [x] `Unresolved import` - Detects imports that do not resolve to a package, type, or static member.
- [x] `'*' import` - Detects on-demand package or static imports.
- [ ] `Missorted imports` - Enforce code-style import ordering.
- [ ] `Single class import` - Track explicit class imports where style policy matters.
- [ ] `Static import` - Track static imports for style or readability review.
- [ ] `Static import can be used based on the auto-import table` - Detect qualifiers that could become configured static imports.
- [ ] `Unnecessary import from the 'java.lang' package` - Remove redundant `java.lang` imports.
- [ ] `Unnecessary import from the same package` - Remove imports of sibling-package types.
- [ ] `Unused import` - Remove dead imports.

## Inheritance issues

- [ ] `Abstract class extends concrete class` - Detect abstraction layers built on concrete implementations.
- [ ] `Abstract class which has no concrete subclass` - Find abstract classes with no realizations.
- [ ] `Abstract class without 'abstract' methods` - Detect abstract classes that do not actually define abstraction points.
- [ ] `Abstract method overrides abstract method` - Flag repeated abstract declarations in the hierarchy.
- [ ] `Abstract method overrides concrete method` - Detect abstract redeclaration of implemented behavior.
- [x] `Abstract method with missing implementations` - Detects concrete classes and records that fail to implement required inherited abstract methods.
- [ ] `Class explicitly extends a 'Collection' class` - Discourage inheriting concrete collection implementations.
- [ ] `Class extends annotation interface` - Detect illegal or nonsensical extension of annotation interfaces.
- [ ] `Class extends utility class` - Flag inheritance from utility-only types.
- [ ] `Class may extend a commonly used base class` - Suggest better-known base abstractions when applicable.
- [ ] `Final declaration can't be overridden at runtime` - Detect `final` members/classes that break framework subclassing/proxy expectations.
- [ ] `Interface which has no concrete subclass` - Find interfaces never implemented concretely.
- [ ] `Method does not call super method` - Flag overrides that skip required or expected `super` behavior.
- [ ] `Method is identical to its super method` - Remove overrides that add no behavior.
- [ ] `Missing '@Override' annotation` - Require explicit override markers.
- [ ] `Non-varargs method overrides varargs method` - Detect override signature mismatches involving varargs.
- [ ] `Parameter type prevents overriding` - Find visually similar but package-different parameter types that break overriding.
- [ ] `Public constructor in abstract class` - Flag instantiation-oriented constructors on abstract types.
- [ ] `Redundant interface declaration` - Remove interfaces already inherited through a superclass or superinterface.
- [ ] `Static inheritance` - Detect interfaces used only to inherit constants.
- [ ] `Type parameter extends 'final' class` - Flag generic bounds that cannot vary beyond a final type.

## Initialization

- [ ] `'this' reference escaped in object construction` - Detect `this` escaping before construction completes.
- [ ] `Abstract method called during object construction` - Flag constructor-time calls to abstract methods.
- [ ] `Double brace initialization` - Detect double-brace initialization and its hidden class/allocation costs.
- [-] `Instance field may not be initialized` - Partially covered through definite-assignment checks for final fields, but not general non-final field initialization analysis.
- [ ] `Instance field used before initialization` - Detect reads of instance fields before they are initialized.
- [ ] `Non-final static field is used during class initialization` - Flag mutable static state used during class init.
- [ ] `Overridable method called during object construction` - Detect constructor-time calls to non-final overridable methods.
- [ ] `Overridden method called during object construction` - Detect calls to methods whose runtime dispatch may hit subclass behavior during construction.
- [-] `Static field may not be initialized` - Partially covered through definite-assignment checks for final fields, but not general static-field initialization analysis.
- [ ] `Static field used before initialization` - Detect reads of static fields before safe initialization.
- [ ] `Unsafe lazy initialization of 'static' field` - Flag racy lazy-init patterns for static state.

## Java language level issues

- [ ] `'assert' statement` - Track `assert` usage relative to target language/runtime compatibility.
- [ ] `Annotation` - Detect annotation usage when targeting an older language level.
- [ ] `Annotation interface` - Detect annotation type declarations on unsupported language levels.
- [ ] `Enhanced 'for' statement` - Track foreach syntax against language level requirements.
- [ ] `Enumerated class` - Detect enum declarations when targeting older Java versions.
- [ ] `Forward compatibility` - Flag identifiers or constructs likely to become invalid in future Java versions.
- [ ] `Varargs method` - Detect variable-arity methods for downgrade or compatibility analysis.

## Numeric issues

- [ ] `'char' expression used in arithmetic context` - Flag `char` arithmetic that can be surprising.
- [ ] `'equals()' called on 'BigDecimal'` - Detect scale-sensitive `BigDecimal.equals()` comparisons.
- [ ] `'long' literal ending with 'l' instead of 'L'` - Prefer uppercase `L` to avoid confusion with `1`.
- [ ] `Call to 'BigDecimal' method without a rounding mode argument` - Require explicit rounding where division/scale operations need it.
- [ ] `Comparison of 'short' and 'char' values` - Flag suspicious comparisons across small integral types.
- [ ] `Comparison to 'Double.NaN' or 'Float.NaN'` - Replace with `isNaN` semantics.
- [ ] `Confusing floating-point literal` - Detect float/double literals that are easy to misread.
- [ ] `Constant call to 'Math'` - Replace `Math` calls with compile-time constants when possible.
- [ ] `Division by zero` - Detect definite divide-by-zero or modulo-by-zero.
- [ ] `Floating-point equality comparison` - Flag direct `==`/`!=` on floating-point values.
- [ ] `Implicit numeric conversion` - Detect silent widening/narrowing conversions.
- [ ] `Integer division in floating-point context` - Flag truncated integer division feeding float/double usage.
- [ ] `Negative int hexadecimal constant in long context` - Detect surprising sign behavior with hex literals.
- [ ] `Non-reproducible call to 'Math'` - Flag math calls whose results are not guaranteed bit-for-bit reproducible.
- [ ] `Number constructor call with primitive argument` - Replace boxed-number constructors with valueOf/autoboxing.
- [ ] `Numeric overflow` - Detect compile-time or data-flow-visible overflow.
- [ ] `Octal and decimal integers in same array` - Flag mixed-base literals in one initializer.
- [ ] `Octal integer` - Detect octal integer literals.
- [ ] `Overly complex arithmetic expression` - Flag arithmetic with too many terms.
- [ ] `Pointless arithmetic expression` - Remove identity or no-op arithmetic.
- [ ] `Possibly lossy implicit cast in compound assignment` - Detect hidden narrowing in `+=`, `*=`, and similar operators.
- [ ] `Suspicious oddness check` - Flag `% 2 == 1` patterns that fail for negatives.
- [ ] `Suspicious underscore in number literal` - Detect digit grouping that suggests accidental formatting.
- [ ] `Unary plus` - Flag redundant unary `+`.
- [ ] `Underscores in numeric literal` - Track underscore-separated numeric literals based on style/compatibility policy.
- [ ] `Unnecessary unary minus` - Remove negation that has no semantic effect.
- [ ] `Unpredictable 'BigDecimal' constructor call` - Flag `new BigDecimal(double)` precision traps.
- [ ] `Unreadable numeric literal` - Require separators in long numeric literals.

## Resource management

- [ ] `'Channel' opened but not safely closed` - Detect leaked `Channel` resources.
- [ ] `AutoCloseable used without 'try'-with-resources` - Require TWR for closeable resources.
- [ ] `Hibernate resource opened but not safely closed` - Detect leaked Hibernate sessions where applicable.
- [ ] `I/O resource opened but not safely closed` - Detect leaked stream/reader/writer resources.
- [ ] `JDBC resource opened but not safely closed` - Detect leaked DB resources.
- [ ] `JNDI resource opened but not safely closed` - Detect leaked naming resources.
- [ ] `Socket opened but not safely closed` - Detect leaked sockets.
- [ ] `Use of 'DriverManager' to get JDBC connection` - Flag direct `DriverManager` acquisition where pooled/data-source usage is preferred.

## Serialization issues

- [ ] `'@Serial' annotation can be used` - Add `@Serial` where serialization protocol members support it.
- [ ] `'@Serial' annotation used on wrong member` - Detect misapplied `@Serial`.
- [ ] `'Comparator' class not declared 'Serializable'` - Flag comparators likely to be stored or transported but not serializable.
- [ ] `'Externalizable' class without 'public' no-arg constructor` - Enforce required constructor shape.
- [ ] `'readObject()' or 'writeObject()' not declared 'private'` - Enforce correct serialization hook visibility.
- [ ] `'readResolve()' or 'writeReplace()' not declared 'protected'` - Enforce serialization replacement hook visibility.
- [ ] `'record' contains ignored members` - Detect serialization members ignored by record semantics.
- [ ] `'Serializable' object implicitly stores non-'Serializable' object` - Find hidden non-serializable captures or references.
- [ ] `'serialPersistentFields' field not declared 'private static final ObjectStreamField[]'` - Enforce exact declaration contract.
- [ ] `'serialVersionUID' field not declared 'private static final long'` - Enforce exact `serialVersionUID` declaration.
- [ ] `Externalizable class with 'readObject()' or 'writeObject()'` - Detect conflicting serialization protocols.
- [ ] `Instance field may not be initialized by 'readObject()'` - Find fields left unsafe after deserialization.
- [ ] `Non-serializable class with 'readObject()' or 'writeObject()'` - Flag serialization hooks on non-serializable types.
- [ ] `Non-serializable class with 'serialVersionUID'` - Flag stray `serialVersionUID` declarations.
- [ ] `Non-serializable field in a 'Serializable' class` - Detect incompatible field types in serializable objects.
- [ ] `Non-serializable object passed to 'ObjectOutputStream'` - Flag direct attempts to write non-serializable objects.
- [ ] `Serializable class with unconstructable ancestor` - Detect serializable types whose non-serializable ancestor lacks a no-arg constructor.
- [ ] `Serializable class without 'readObject()' and 'writeObject()'` - Track serializable classes missing explicit custom protocol hooks where required by policy.
- [ ] `Serializable non-'static' inner class with non-Serializable outer class` - Flag inner/outer serializability mismatch.
- [ ] `Serializable non-static inner class without 'serialVersionUID'` - Require explicit UID on serializable inner classes.
- [ ] `Transient field in non-serializable class` - Flag meaningless `transient` fields.
- [ ] `Transient field is not initialized on deserialization` - Detect transient state not restored by `readObject`.

## Visibility

- [ ] `'public' constructor in non-public class` - Flag public constructors on types that still are not publicly accessible.
- [ ] `Access to inherited field looks like access to element from surrounding code` - Detect confusing inherited-field shadowing in inner scopes.
- [ ] `Anonymous class variable hides variable in containing method` - Flag anonymous-class fields hiding locals or parameters.
- [ ] `Call to inherited method looks like call to local method` - Detect inherited method calls that read like local method calls from surrounding scope.
- [ ] `Class is exposed outside of its visibility scope` - Detect method/field signatures leaking less-visible types.
- [ ] `Empty 'module-info.java' file` - Flag empty module descriptors.
- [ ] `Inner class field hides outer class field` - Detect nested-field shadowing.
- [ ] `Lambda parameter hides field` - Flag lambda parameter names that shadow fields.
- [ ] `Local variable hides field` - Flag locals that shadow fields.
- [ ] `Method overrides inaccessible method of superclass` - Detect same-signature methods that do not actually override due to visibility.
- [x] `Method tries to override 'static' method of superclass` - Covered by invalid-override detection for static/non-static mismatches.
- [ ] `Module exports/opens package to itself` - Detect self-export/self-open directives in JPMS.
- [ ] `Parameter hides field` - Flag parameter names shadowing fields.
- [ ] `Pattern variable hides field` - Flag pattern variables shadowing fields.
- [ ] `Possibly unintended overload of method from superclass` - Detect near-override overloads with incompatible parameter types.
- [ ] `Subclass field hides superclass field` - Flag field shadowing across class hierarchies.
- [ ] `Type parameter hides visible type` - Detect generic parameter names that shadow visible classes.
- [ ] `Usage of service not declared in 'module-info'` - Require `uses` declarations for `ServiceLoader` consumption in modules.

## Verbose or redundant code constructs

- [ ] `'StringBuilder' can be replaced with 'String'` - Replace trivial builder usage with plain concatenation.
- [ ] `Cast can be replaced with variable` - Reuse an existing variable or pattern variable instead of recasting.
- [ ] `Comparator method can be simplified` - Simplify comparator combinator chains.
- [ ] `Concatenation with empty string` - Remove empty-string concatenation hacks.
- [ ] `Condition is covered by further condition` - Remove earlier conditions made redundant by later ones.
- [ ] `Duplicate branches in 'switch'` - Merge equal switch branches.
- [ ] `Excessive lambda usage` - Replace trivial lambdas with simpler direct forms when available.
- [ ] `Excessive range check` - Collapse multi-branch range checks into a single clearer condition.
- [ ] `Explicit array filling` - Replace manual filling loops with `Arrays.fill` or `Arrays.setAll`.
- [ ] `Manual min/max calculation` - Replace manual comparisons with `Math.min`/`Math.max`.
- [ ] `Multiple occurrences of the same expression` - Extract repeated equivalent expressions.
- [ ] `Non-strict inequality '>=' or '<=' can be replaced with '=='` - Narrow inequalities that data flow proves single-valued.
- [ ] `Null-check method is called with obviously non-null argument` - Remove `requireNonNull`-style checks on values already known non-null.
- [ ] `Only one element is used` - Detect containers created only to immediately access a single element.
- [ ] `Optional call chain can be simplified` - Collapse verbose optional pipelines.
- [ ] `Redundant 'Collection' operation` - Replace unnecessarily complex collection usage with a simpler equivalent.
- [ ] `Redundant 'compare()' method call` - Remove superfluous `compare()` wrapping in comparisons.
- [ ] `Redundant 'File' instance creation` - Pass paths directly where `File` allocation is unnecessary.
- [ ] `Redundant 'isInstance()' or 'cast()' call` - Remove needless `Class.isInstance` or `Class.cast` use.
- [ ] `Redundant 'String' operation` - Remove unnecessary `String` constructors or no-op string methods.
- [ ] `Redundant array creation` - Remove array allocation used only for varargs passing.
- [ ] `Redundant array length check` - Remove array length guards rendered unnecessary by the iteration pattern.
- [ ] `Redundant escape in regex replacement string` - Remove needless escaping in replacement text.
- [ ] `Redundant operation on 'java.time' object` - Remove no-op or avoidable `java.time` transformations.
- [ ] `Redundant step in 'Stream' or 'Optional' call chain` - Remove identity `map`, useless `filter`, redundant `sorted`, and similar steps.
- [ ] `Redundant type arguments` - Rely on compiler inference instead of explicit generic arguments.
- [ ] `Redundant type cast` - Remove unnecessary casts.
- [ ] `Replacement operation has no effect` - Detect `replace`/`replaceAll` calls that cannot change the string.
- [ ] `Simplifiable collector` - Reduce a collector pipeline to a simpler standard collector.
- [ ] `Stream API call chain can be simplified` - Collapse verbose stream pipelines.
- [ ] `Too weak variable type leads to unnecessary cast` - Narrow a declaration type to remove casts.
- [ ] `Unnecessarily escaped character` - Remove escapes not required in literals.
- [ ] `Unnecessary 'break' statement` - Remove dead or redundant `break`.
- [ ] `Unnecessary 'continue' statement` - Remove trailing or redundant `continue`.
- [ ] `Unnecessary 'default' for enum 'switch' statement` - Remove impossible `default` branches from exhaustive enum switches.
- [ ] `Unnecessary 'return' statement` - Remove terminal `return;` in `void` methods and constructors.
- [ ] `Unnecessary label on 'break' statement` - Remove labels on `break` when not needed.
- [ ] `Unnecessary label on 'continue' statement` - Remove labels on `continue` when not needed.
- [x] `Unreachable catch section` - Detects catch blocks that can never be matched.

## High-priority probable bugs

- [ ] `'assert' statement with side effects` - Detect asserts whose condition mutates state.
- [ ] `'Comparable' implemented but 'equals()' not overridden` - Enforce consistency between ordering and equality contracts.
- [ ] `'equals()' and 'hashCode()' not paired` - Require both methods to be implemented together.
- [ ] `'equals()' between objects of inconvertible types` - Flag impossible equality checks.
- [ ] `'equals()' called on array` - Use `Arrays.equals`/`deepEquals` instead of reference-equality semantics.
- [ ] `'equals()' called on itself` - Detect self-comparison that is always true.
- [ ] `'instanceof' with incompatible type` - Flag impossible type tests.
- [ ] `'Math.random()' cast to 'int'` - Detect the classic always-zero bug.
- [ ] `'Throwable' not thrown` - Flag exception objects created and then ignored.
- [ ] `Array comparison using '==', instead of 'Arrays.equals()'` - Replace reference comparison for arrays.
- [ ] `Call to 'toString()' on array` - Replace array `toString` misuse with proper array formatting helpers.
- [ ] `Cast conflicts with 'instanceof'` - Detect casts incompatible with an earlier type test.
- [ ] `Cast to incompatible type` - Flag impossible casts.
- [ ] `Collection added to itself` - Detect self-add/self-put container bugs.
- [ ] `Confusing 'main()' method` - Flag methods named `main` that are not actual entry points.
- [ ] `Confusing argument to varargs method` - Detect null/array varargs calls with ambiguous behavior.
- [ ] `Constant condition in 'assert' statement` - Find asserts that always succeed or fail trivially.
- [ ] `Constant values` - Detect expressions or conditions proven constant by analysis.
- [ ] `Copy constructor misses field` - Find copy constructors that omit part of object state.
- [ ] `Covariant 'equals()'` - Detect `equals(T)` without proper `equals(Object)`.
- [ ] `Expression is compared to itself` - Flag `x == x`-style accidental self-comparisons.
- [ ] `Infinite recursion` - Detect self-calls that never bottom out.
- [ ] `Invalid method reference used for 'Comparator'` - Flag method references that violate comparator contract expectations.
- [ ] `Loop executes zero or billions of times` - Detect loops that do not terminate as intended due to overflow or bounds errors.
- [ ] `Malformed format string` - Validate `String.format` style placeholders.
- [ ] `Nullability and data flow problems` - Surface definite nullability violations and other data-flow-proven bugs.
- [ ] `Number comparison using '==', instead of 'equals()'` - Detect boxed-number reference comparison.
- [ ] `Object comparison using '==', instead of 'equals()'` - Detect accidental reference equality.
- [ ] `Optional.get() is called without isPresent() check` - Detect unsafe optional extraction.
- [ ] `Result of method call ignored` - Flag ignored return values from meaningful methods.
- [ ] `Result of object allocation ignored` - Detect `new` expressions whose result is unused.
- [ ] `Sorted collection with non-comparable elements` - Detect natural-order sorted collections whose element type cannot be ordered.
- [-] `Statement with empty body` - Partially covered via dedicated checks for empty `catch`, `switch`, and `synchronized` bodies.
- [ ] `String comparison using '==', instead of 'equals()'` - Detect reference comparison on strings.
- [ ] `Subtraction in 'compareTo()'` - Flag overflow-prone subtraction-based comparison.
- [ ] `Suspicious 'Collection.toArray()' call` - Detect incorrect or misleading `toArray` usage.
- [ ] `Suspicious 'Comparator.compare()' implementation` - Validate comparator contract behavior.
- [ ] `Suspicious 'List.remove()' in loop` - Flag ascending-index removal loops that skip elements.
- [ ] `Suspicious 'System.arraycopy()' call` - Detect invalid or mismatched array copy arguments.
- [ ] `Suspicious array cast` - Flag array casts likely to fail at runtime.
- [ ] `Suspicious collection method call` - Detect generic collection calls with the wrong apparent element type.
- [ ] `Suspicious indentation after control statement without braces` - Flag misleading indentation that hides actual scope.
- [ ] `Suspicious integer division assignment` - Detect truncating integer division assigned where a precise result seems intended.
- [ ] `Suspicious regex expression argument` - Detect regex metacharacters passed where literal intent seems likely.
- [x] `Unreachable code` - Detects statements after terminating control flow and related unreachable regions.
- [ ] `Unsafe call to 'Class.newInstance()'` - Replace deprecated/unsafe reflective instantiation.
- [ ] `Unused assignment` - Detect assignments overwritten or never subsequently observed.
- [ ] `Use of Optional.ofNullable with null or non-null argument` - Replace `ofNullable` when argument nullability is already known.
- [ ] `Use of shallow or 'Objects' methods with arrays` - Detect wrong array equality/hash helpers.
- [ ] `Whitespace may be missing in string concatenation` - Flag concatenations likely missing a separating space.
- [ ] `Write-only object` - Detect objects that are mutated but never meaningfully read.
- [ ] `Wrong package statement` - Detect package declarations that do not match directory structure.

## Suggested implementation order

1. Parser and semantic model prerequisites.
2. Definite assignment, definite unassignment, and initialization checks.
3. Visibility, inheritance, and override resolution checks.
4. Control-flow and reachability checks.
5. Nullability/data-flow and high-value probable bug checks.
6. Numeric, exception, resource, and serialization checks.
7. Style, redundancy, and simplification inspections.

Note: This file was initially generated and status-annotated with AI assistance, then grounded against JetBrains Inspectopedia and the current Railroad codebase.

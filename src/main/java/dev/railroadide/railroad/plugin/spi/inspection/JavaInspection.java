package dev.railroadide.railroad.plugin.spi.inspection;

/**
 * Legacy extension point for plugin-provided Java inspections.
 *
 * Prefer {@link JavaInspectionRuleProvider} for new work so rule metadata, settings, and
 * diagnostics can be managed at the rule level instead of the whole inspection level.
 */
@Deprecated(forRemoval = false)
public interface JavaInspection {
    /**
     * Stable inspection id, ideally namespaced (e.g. {@code my.plugin:rule-id}).
     */
    String id();

    /**
     * Runs inspection logic and reports diagnostics via {@link JavaInspectionReporter}.
     */
    void inspect(JavaInspectionContext context, JavaInspectionReporter reporter);
}

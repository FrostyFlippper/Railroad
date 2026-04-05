package dev.railroadide.railroad.plugin.spi.inspection;

import java.util.List;

/**
 * Provider of Java inspection rules.
 * <p>
 * Implement this interface to contribute one or more related rules. Providers are the
 * main public extension point for new inspection work.
 */
public interface JavaInspectionRuleProvider {
    /**
     * Stable provider id.
     *
     * @return stable provider id
     */
    String id();

    /**
     * Rules contributed by this provider.
     * <p>
     * The returned list should be stable for the lifetime of the provider.
     *
     * @return rules contributed by this provider
     */
    List<JavaInspectionRule> rules();
}

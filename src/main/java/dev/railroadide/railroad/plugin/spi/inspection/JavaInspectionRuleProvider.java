package dev.railroadide.railroad.plugin.spi.inspection;

import java.util.List;

/**
 * Provider of Java inspection rules.
 */
public interface JavaInspectionRuleProvider {
    /**
     * Stable provider id.
     */
    String id();

    /**
     * Rules contributed by this provider.
     */
    List<JavaInspectionRule> rules();
}

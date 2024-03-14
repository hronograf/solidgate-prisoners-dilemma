package org.example.statistics;

import org.example.strategy.StrategyName;

public record PerformanceResult (
  StrategyName strategyName,
  Long executionInMillis
) {}

package org.example.statistics;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.example.strategy.StrategyName;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class StrategyStatistics {
  private final StrategyName strategyName;
  private Integer numberOfWins = 0;
  private Integer numberOfPoints = 0;
}

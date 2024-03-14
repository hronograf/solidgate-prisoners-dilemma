package org.example.statistics;

import org.example.game.StrategyGameResult;
import org.example.strategy.StrategyName;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StrategyStatisticsExtractor {
  public List<StrategyStatistics> extract(List<StrategyGameResult> results) {
    Map<StrategyName, StrategyStatistics> statisticsByNameMap = new HashMap<>();
    results.forEach(
      result -> statisticsByNameMap.compute(
        result.getStrategyName(),
        (name, statistics) -> getStatistics(result, statistics)
      )
    );

    return statisticsByNameMap.values().stream()
      .sorted(Comparator.comparingInt(StrategyStatistics::getNumberOfWins).reversed())
      .toList();
  }

  private StrategyStatistics getStatistics(
    StrategyGameResult result,
    StrategyStatistics statistics
  ) {
    return Optional.ofNullable(statistics)
      .map(
        s -> s
          .setNumberOfPoints(s.getNumberOfPoints() + result.getNumberOfPoints())
          .setNumberOfWins(s.getNumberOfWins() + (result.isWinner() ? 1 : 0))
      )
      .orElseGet(() -> new StrategyStatistics(result.getStrategyName()));
  }
}

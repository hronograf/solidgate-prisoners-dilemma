package org.example.writer.impl;

import org.example.statistics.StrategyStatistics;
import org.example.writer.StrategyStatisticsWriter;
import java.util.List;

public class StrategyStatisticsConsoleWriter implements StrategyStatisticsWriter {
  @Override
  public void write(List<StrategyStatistics> allStatistics) {
    for (int i = 0; i < allStatistics.size(); i++) {
      StrategyStatistics statistics = allStatistics.get(i);
      System.out.println(
        i + ". " + statistics.getStrategyName().name()
          + " - wins: " + statistics.getNumberOfWins()
          + " total points: " + statistics.getNumberOfPoints()
      );
    }
  }
}

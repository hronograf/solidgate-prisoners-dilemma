package org.example.writer;

import org.example.statistics.StrategyStatistics;
import java.util.List;

public interface StrategyStatisticsWriter {
  void write(List<StrategyStatistics> statistics);
}

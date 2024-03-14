package org.example.writer;

import org.example.statistics.PerformanceResult;
import java.util.List;

public interface PerformanceResultsWriter {
  void write(List<PerformanceResult> performanceResults);
}

package org.example.writer.impl;

import org.example.statistics.PerformanceResult;
import org.example.writer.PerformanceResultsWriter;
import java.util.List;

public class PerformanceResultsConsoleWriter implements PerformanceResultsWriter {
  @Override
  public void write(List<PerformanceResult> performanceResults) {
    for (int i = 0; i < performanceResults.size(); i++) {
      PerformanceResult result = performanceResults.get(i);
      System.out.println(
        i + ". " + result.strategyName().name()
          + " - totalExecutionTime: " + result.executionInMillis()
      );
    }
  }
}

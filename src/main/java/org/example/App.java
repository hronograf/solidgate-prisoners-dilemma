package org.example;

import org.example.game.Tournament;
import org.example.game.StrategyGameResult;
import org.example.statistics.PerformanceResult;
import org.example.statistics.StrategyPerformanceTester;
import org.example.statistics.StrategyStatisticsExtractor;
import org.example.strategy.Strategy;
import org.example.strategy.impl.CooperativeStrategy;
import org.example.strategy.impl.DefectiveStrategy;
import org.example.strategy.impl.TitForTatStrategy;
import org.example.strategy.impl.WinStayLoseSwitchStrategy;
import org.example.game.rules.GameRules;
import org.example.game.rules.impl.IterativeGameRules;
import org.example.writer.PerformanceResultsWriter;
import org.example.writer.StrategyStatisticsWriter;
import org.example.writer.impl.PerformanceResultsConsoleWriter;
import org.example.writer.impl.StrategyStatisticsConsoleWriter;
import java.util.Comparator;
import java.util.List;

public class App {
  private static final Integer NUMBER_OF_ROUNDS_IN_GAME = 60;
  private static final Integer NUMBER_OF_PERFORMANCE_ROUNDS = 1_000_000;
  private static final Integer NUMBER_OF_PERFORMANCE_TESTS = 10;

  public static void main(String[] args) {
    List<Strategy> strategies = List.of(
      new CooperativeStrategy(),
      new DefectiveStrategy(),
      new TitForTatStrategy(),
      new WinStayLoseSwitchStrategy()
    );
    GameRules rules = new IterativeGameRules(10, 7, 3, 1);
    Tournament tournament = new Tournament(rules);
    List<StrategyGameResult> results = tournament.run(strategies, NUMBER_OF_ROUNDS_IN_GAME);

    StrategyStatisticsExtractor statisticsExtractor = new StrategyStatisticsExtractor();
    StrategyStatisticsWriter statisticsWriter = new StrategyStatisticsConsoleWriter();
    System.out.println("Game statistics for " + NUMBER_OF_ROUNDS_IN_GAME + " rounds:");
    statisticsWriter.write(statisticsExtractor.extract(results));
    System.out.println();

    StrategyPerformanceTester performanceTester = new StrategyPerformanceTester();
    PerformanceResultsWriter performanceResultsWriter = new PerformanceResultsConsoleWriter();
    List<PerformanceResult> performanceResults = strategies.stream()
      .map(
        strategy -> performanceTester
          .test(rules, strategy, NUMBER_OF_PERFORMANCE_ROUNDS, NUMBER_OF_PERFORMANCE_TESTS)
      )
      .sorted(Comparator.comparingLong(PerformanceResult::executionInMillis))
      .toList();
    System.out.println(
      "Performance results for " + NUMBER_OF_PERFORMANCE_ROUNDS + " rounds and "
        + NUMBER_OF_PERFORMANCE_TESTS + " tests:"
    );
    performanceResultsWriter.write(performanceResults);
  }
}

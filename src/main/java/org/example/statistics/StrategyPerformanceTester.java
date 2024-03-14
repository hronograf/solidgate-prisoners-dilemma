package org.example.statistics;

import org.example.game.Game;
import org.example.game.rules.GameRules;
import org.example.strategy.Strategy;

public class StrategyPerformanceTester {
  public PerformanceResult test(
    GameRules rules,
    Strategy strategy,
    Integer numberOfRounds,
    Integer numberOfTests
  ) {
    long totalExecutionTime = 0;
    for (int i = 0; i < numberOfTests; i++) {
      totalExecutionTime += test(rules, strategy, numberOfRounds);
    }

    return new PerformanceResult(strategy.getName(), totalExecutionTime / numberOfTests);
  }

  private long test(GameRules rules, Strategy strategy, Integer numberOfRounds) {
    Game game = new Game(rules, strategy, strategy);
    Long systemTimeBeforeGame = System.currentTimeMillis();
    game.play(numberOfRounds);
    Long systemTimeAfterGame = System.currentTimeMillis();
    return systemTimeAfterGame - systemTimeBeforeGame;
  }
}

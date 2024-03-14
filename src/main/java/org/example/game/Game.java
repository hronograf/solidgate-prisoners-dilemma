package org.example.game;

import lombok.RequiredArgsConstructor;
import org.example.strategy.Decision;
import org.example.strategy.Strategy;
import org.example.game.rules.GameRules;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class Game {
  private final GameRules rules;
  private final Strategy strategy1;
  private final Strategy strategy2;

  public List<StrategyGameResult> play(Integer numberOfRounds) {
    StrategyGameResult strategy1Result = new StrategyGameResult(strategy1.getName());
    StrategyGameResult strategy2Result = new StrategyGameResult(strategy2.getName());

    for (int i = 0; i < numberOfRounds; i++) {
      Decision decision1 = strategy1.decide(strategy1Result, strategy2Result, rules);
      Decision decision2 = strategy2.decide(strategy2Result, strategy1Result, rules);
      Map<Decision, Integer> points = rules.calculatePoints(decision1, decision2);

      strategy1Result.addDecision(decision1, points.get(decision1));
      strategy2Result.addDecision(decision2, points.get(decision2));
    }

    strategy1Result
      .isWinner(strategy1Result.getNumberOfPoints() > strategy2Result.getNumberOfPoints());
    strategy2Result
      .isWinner(strategy2Result.getNumberOfPoints() > strategy1Result.getNumberOfPoints());

    return List.of(strategy1Result, strategy2Result);
  }
}

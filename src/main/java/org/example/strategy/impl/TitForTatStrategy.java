package org.example.strategy.impl;

import org.example.strategy.StrategicPlayer;
import org.example.strategy.Decision;
import org.example.strategy.Strategy;
import org.example.strategy.StrategyName;
import org.example.game.rules.GameRules;
import java.util.List;

public class TitForTatStrategy implements Strategy {
  @Override
  public Decision decide(StrategicPlayer player, StrategicPlayer opponent, GameRules rules) {
    List<Decision> opponentDecisions = opponent.getDecisions();
    if (opponentDecisions.isEmpty()) {
      return Decision.COOPERATE;
    }

    return opponentDecisions.get(opponentDecisions.size() - 1);
  }

  @Override
  public StrategyName getName() {
    return StrategyName.TIT_FOR_TAT;
  }
}

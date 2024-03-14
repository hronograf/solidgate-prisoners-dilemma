package org.example.strategy.impl;

import org.example.strategy.StrategicPlayer;
import org.example.strategy.Decision;
import org.example.strategy.Strategy;
import org.example.strategy.StrategyName;
import org.example.game.rules.GameRules;

public class CooperativeStrategy implements Strategy {
  @Override
  public Decision decide(StrategicPlayer player, StrategicPlayer opponent, GameRules rules) {
    return Decision.COOPERATE;
  }

  @Override
  public StrategyName getName() {
    return StrategyName.COOPERATIVE;
  }
}

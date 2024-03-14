package org.example.strategy;

import org.example.game.rules.GameRules;

public interface Strategy {
  Decision decide(StrategicPlayer player, StrategicPlayer opponent, GameRules rules);

  StrategyName getName();
}

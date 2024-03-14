package org.example.strategy.impl;

import org.example.strategy.StrategicPlayer;
import org.example.strategy.Decision;
import org.example.strategy.Strategy;
import org.example.strategy.StrategyName;
import org.example.game.rules.GameRules;
import java.util.List;
import java.util.Map;

public class WinStayLoseSwitchStrategy implements Strategy {
  @Override
  public Decision decide(StrategicPlayer player, StrategicPlayer opponent, GameRules rules) {
    List<Decision> playerDecisions = player.getDecisions();
    List<Decision> opponentDecisions = opponent.getDecisions();
    if (playerDecisions.isEmpty() || opponentDecisions.isEmpty()) {
      return Decision.COOPERATE;
    }

    Decision playerLastDecision = playerDecisions.get(playerDecisions.size() - 1);
    Decision opponentLastDecision = opponentDecisions.get(opponentDecisions.size() - 1);
    Map<Decision, Integer> pointsByDecisionMap = rules
      .calculatePoints(playerLastDecision, opponentLastDecision);

    Integer playerLastPoints = pointsByDecisionMap.get(playerLastDecision);
    Integer opponentLastPoints = pointsByDecisionMap.get(opponentLastDecision);
    if (playerLastPoints >= opponentLastPoints) {
      return playerLastDecision;
    }

    return opponentLastDecision;
  }

  @Override
  public StrategyName getName() {
    return StrategyName.WIN_STAY_LOSE_SWITCH;
  }
}

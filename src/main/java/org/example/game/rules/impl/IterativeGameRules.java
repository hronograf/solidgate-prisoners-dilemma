package org.example.game.rules.impl;

import org.example.strategy.Decision;
import org.example.game.rules.GameRules;
import java.util.Map;

public class IterativeGameRules implements GameRules {
  private final Integer defectionOnCooperationPoints;
  private final Integer cooperationOnCooperationPoints;
  private final Integer defectionOnDefectionPoints;
  private final Integer cooperationOnDefectionPoints;

  public IterativeGameRules(
    Integer defectionOnCooperationPoints,
    Integer cooperationOnCooperationPoints,
    Integer defectionOnDefectionPoints,
    Integer cooperationOnDefectionPoints
  ) {
    if (
      !areLimitationsSatisfied(
        defectionOnCooperationPoints,
        cooperationOnCooperationPoints,
        defectionOnDefectionPoints,
        cooperationOnDefectionPoints
      )
    ) {
      throw new IllegalArgumentException("Unsatisfied iterative rules limitations");
    }

    this.cooperationOnCooperationPoints = cooperationOnCooperationPoints;
    this.defectionOnCooperationPoints = defectionOnCooperationPoints;
    this.cooperationOnDefectionPoints = cooperationOnDefectionPoints;
    this.defectionOnDefectionPoints = defectionOnDefectionPoints;
  }

  @Override
  public Map<Decision, Integer> calculatePoints(Decision decision1, Decision decision2) {
    if (decision1.isCooperate() && decision2.isCooperate()) {
      return Map.of(Decision.COOPERATE, cooperationOnCooperationPoints);
    }

    if (decision1.isDefect() && decision2.isCooperate()) {
      return Map.of(
        Decision.DEFECT, defectionOnCooperationPoints,
        Decision.COOPERATE, cooperationOnDefectionPoints
      );
    }

    if (decision1.isCooperate() && decision2.isDefect()) {
      return Map.of(
        Decision.COOPERATE, cooperationOnDefectionPoints,
        Decision.DEFECT, defectionOnCooperationPoints
      );
    }

    if (decision1.isDefect() && decision2.isDefect()) {
      return Map.of(Decision.DEFECT, defectionOnDefectionPoints);
    }

    throw new IllegalArgumentException("No rule for such decisions");
  }

  private boolean areLimitationsSatisfied(
    Integer defectionOnCooperationPoints,
    Integer cooperationOnCooperationPoints,
    Integer defectionOnDefectionPoints,
    Integer cooperationOnDefectionPoints
  ) {
    boolean meetBasicLimitations = defectionOnCooperationPoints > cooperationOnCooperationPoints
      && cooperationOnCooperationPoints > defectionOnDefectionPoints
      && defectionOnDefectionPoints > cooperationOnDefectionPoints;
    boolean meetIterativeLimitations = 2 * cooperationOnCooperationPoints
      > defectionOnCooperationPoints + cooperationOnDefectionPoints;
    return meetBasicLimitations && meetIterativeLimitations;
  }
}

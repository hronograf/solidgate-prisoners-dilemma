package org.example.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.example.strategy.StrategicPlayer;
import org.example.strategy.Decision;
import org.example.strategy.StrategyName;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class StrategyGameResult implements StrategicPlayer {
  private final StrategyName strategyName;
  private final List<Decision> decisions = new ArrayList<>();
  private Integer numberOfPoints = 0;
  @Accessors(fluent = true)
  @Setter
  private Boolean isWinner;

  public void addDecision(Decision decision, Integer pointsDelta) {
    decisions.add(decision);
    numberOfPoints += pointsDelta;
  }
}

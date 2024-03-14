package org.example.game.rules;

import org.example.strategy.Decision;
import java.util.Map;

public interface GameRules {
  Map<Decision, Integer> calculatePoints(Decision decision1, Decision decision2);
}

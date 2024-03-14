package org.example.game;

import lombok.RequiredArgsConstructor;
import org.example.strategy.Strategy;
import org.example.game.rules.GameRules;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Tournament {
  private final GameRules rules;

  public List<StrategyGameResult> run(List<Strategy> strategies, Integer numberOfRoundsInGame) {
    return createGames(strategies).stream()
      .map(game -> game.play(numberOfRoundsInGame))
      .flatMap(List::stream)
      .toList();
  }

  private List<Game> createGames(List<Strategy> strategies) {
    List<Game> games = new ArrayList<>();

    for (Strategy strategy1: strategies) {
      for (Strategy strategy2: strategies) {
        if (strategy1 != strategy2) {
          games.add(new Game(rules, strategy1, strategy2));
        }
      }
    }

    return games;
  }
}

package org.example.strategy;

import java.util.List;

public interface StrategicPlayer {
  List<Decision> getDecisions();

  Integer getNumberOfPoints();
}

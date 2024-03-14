package org.example.strategy;

public enum Decision {
  COOPERATE,
  DEFECT;

  public boolean isCooperate() {
    return this == COOPERATE;
  }

  public boolean isDefect() {
    return this == DEFECT;
  }
}

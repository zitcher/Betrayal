package com.term_project.game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents dice used in the game betrayal.
 *
 * @author Zachary Hoffman
 */
public final class Dice {
  public static final int MAX_DIE = 2;

  /**
   * Classes should have private constructors rather than no constructor.
   *
   */
  private Dice() {
  }

  /**
   * Return's an integer representing a x position.
   *
   * @return An integer representing a x position.
   *
   */
  public static List<Integer> roll(int numRolls) {
    List<Integer> rolls = new ArrayList<>();
    for (int i = 0; i < numRolls; i++) {
      Integer randInt = ThreadLocalRandom.current().nextInt(0, MAX_DIE + 1);
      rolls.add(randInt);
    }
    return rolls;
  }

  public static Integer sum(List<Integer> rolls) {
    int sum = 0;

    for (Integer i : rolls) {
      sum = sum + i;
    }

    return sum;
  }
}

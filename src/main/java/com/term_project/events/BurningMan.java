package com.term_project.events;

import java.util.List;

import com.term_project.character.GameChar;
import com.term_project.game.Stats;

public class BurningMan extends AbstractEvent implements Event {

  public BurningMan(String name, String description, String function,
      List<Stats> usable) {
    super(name, description, function, usable);
  }

  @Override
  public String apply(int roll, GameChar character) {
    if (roll >= 4) {
      character.modSanity(1);
      return "You feel a little hot under the collar, but otherwise fine. Gain 1 Sanity.";
    } else if (roll > 1 && roll < 4) {
      return "The fire burns, but you can stand the heat.";
    } else {
      character.modSanity(-2);
      return "You burst into flames! Take 2 Sanity damage.";
    }
  }
}

package com.term_project.events;

import java.util.List;

import com.term_project.character.GameChar;
import com.term_project.game.Stats;

public class AngryBeing extends AbstractEvent implements Event {

  public AngryBeing(String name, String description, String function,
      List<Stats> usable) {
    super(name, description, function, usable);
  }

  @Override
  public String apply(int roll, GameChar character) {
    if (roll >= 5) {
      character.modSpeed(1);
      return "You get away. Gain 1 Speed";
    } else if (roll < 5 && roll > 1) {
      character.modSanity(-1);
      return "Take 1 Sanity damage.";
    } else {
      character.modSanity(-1);
      character.modSpeed(-1);
      return "Take 1 Sanity damage and 1 Speed damage.";
    }
  }
}

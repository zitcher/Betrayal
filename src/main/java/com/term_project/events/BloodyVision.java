package com.term_project.events;

import java.util.List;

import com.term_project.character.GameChar;
import com.term_project.game.Stats;

public class BloodyVision extends AbstractEvent implements Event {

  public BloodyVision(String name, String description, String function,
      List<Stats> usable) {
    super(name, description, function, usable);
  }

  @Override
  public String apply(int roll, GameChar character) {
    if (roll >= 4) {
      character.modSanity(1);
      return "You steel yourself. Gain 1 Sanity";
    } else if (roll == 2 || roll == 3) {
      character.modSanity(-1);
      return "Lose 1 Sanity";
    } else {
      character.modSanity(1);
      return "Lose 2 Sanity";
    }
  }
}

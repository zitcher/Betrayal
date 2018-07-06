package com.term_project.events;

import java.util.List;

import com.term_project.character.GameChar;
import com.term_project.game.Stats;

public class Funeral extends AbstractEvent implements Event {

  public Funeral(String name, String description, String function,
      List<Stats> usable) {
    super(name, description, function, usable);
  }

  @Override
  public String apply(int roll, GameChar character) {
    if (roll >= 4) {
      character.modSanity(1);
      return "You blink and its gone. Gain 1 Sanity.";
    } else if (roll > 1 && roll < 4) {
      character.modSanity(-1);
      return "The vision disturbs you. Lose 1 Sanity.";
    } else {
      character.modSanity(-1);
      character.modMight(-1);
      return "You're really in that coffin. Lose 1 Sanity and 1 Might.";
    }
  }
}

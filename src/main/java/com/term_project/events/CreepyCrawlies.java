package com.term_project.events;

import java.util.List;

import com.term_project.character.GameChar;
import com.term_project.game.Stats;

public class CreepyCrawlies extends AbstractEvent implements Event {

  public CreepyCrawlies(String name, String description, String function,
      List<Stats> usable) {
    super(name, description, function, usable);
  }

  @Override
  public String apply(int roll, GameChar character) {
    if (roll >= 5) {
      character.modSanity(1);
      return "You blink and they're gone. Gain 1 Sanity.";
    } else if (roll > 0 && roll < 5) {
      character.modSanity(-1);
      return "Lose 1 Sanity.";
    } else {
      character.modSanity(-2);
      return "Lose 2 Sanity.";
    }
  }
}
